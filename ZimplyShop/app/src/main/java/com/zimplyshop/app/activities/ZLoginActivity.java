package com.zimplyshop.app.activities;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.zimplyshop.app.R;

import java.io.IOException;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZLoginActivity extends ZBaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, ResultCallback<People.LoadPeopleResult> {

    Button googleLoginButton;
    ProgressDialog progressDialog;

    // GOOGLE API
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;
    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;
    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    private static final int PROFILE_PIC_SIZE = 400;

    String emailToSend, idToSend, imageUrlToSend, nameToSend,
            accessTokenToSend, additionalDataToSend;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_login_activity_layout);

        if (!checkPlayServices())
            return;

        googleLoginButton = (Button) findViewById(R.id.google_sign_in_button);

        progressDialog = new ProgressDialog(this);
        progressDialog.dismiss();

        googleLoginButton.setOnClickListener(this);

        initialiseGoogleApiClient();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(
                        this,
                        "This device doesn't support Play services, App will not work normally",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_sign_in_button:
                onGoogleSignInClicked();
                break;
        }
    }

    private void onGoogleSignInClicked() {
        mShouldResolve = true;
        mGoogleApiClient.connect();
        progressDialog = ProgressDialog.show(this, "Google Login",
                "Getting Google login details. Please wait..", true, false);
    }

    private void initialiseGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE)).build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
                if (progressDialog != null)
                    progressDialog.dismiss();
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
            if (!progressDialog.isShowing())
                progressDialog = ProgressDialog
                        .show(this, "Google Login",
                                "Logging in through Google..Please Wait..",
                                true, false);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    if (progressDialog != null)
                        progressDialog.dismiss();
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    progressDialog = ProgressDialog.show(this, null,
                            "Getting user account details");
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                Toast.makeText(
                        this,
                        "Login error...Please try again "
                                + connectionResult.describeContents(),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            if (progressDialog != null)
                progressDialog.dismiss();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mShouldResolve = false;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog = ProgressDialog.show(this, null,
                    "Getting user details.Please wait..");

        Plus.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(
                this);

        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi
                    .getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            String personPhoto = currentPerson.getImage().getUrl();
            String personGooglePlusProfile = currentPerson.getUrl();
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
            String id = currentPerson.getId();

            personPhoto = personPhoto.substring(0, personPhoto.length() - 2)
                    + PROFILE_PIC_SIZE;

            nameToSend = personName;
            imageUrlToSend = personPhoto;
            emailToSend = email;
            idToSend = id;
            additionalDataToSend = currentPerson
                    + "  ---   profile url   --   " + personGooglePlusProfile;
        } else {
        }

        GetGoogleIdTokenTask task = new GetGoogleIdTokenTask();
        task.execute();
    }

    private void onGoogleSignOutClicked() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }
        // showSignedOutUI();
    }

    @Override
    public void onConnectionSuspended(int arg0) {

    }

    @Override
    public void onResult(People.LoadPeopleResult arg0) {

    }

    private class GetGoogleIdTokenTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = ProgressDialog.show(ZLoginActivity.this, null,
                    "Getting Google Plus Access Token");
        }

        @Override
        protected String doInBackground(Void... params) {
            String accountName = Plus.AccountApi
                    .getAccountName(mGoogleApiClient);
            Account account = new Account(accountName,
                    GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
            String scopes = "audience:server:client_id:"
                    + "509487250429-pekgqlshg58mfqm6adctmvm3ul9nlbjr.apps.googleusercontent.com";
            try {
                return GoogleAuthUtil.getToken(getApplicationContext(),
                        account, scopes);
            } catch (IOException e) {
                return null;
            } catch (GoogleAuthException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (progressDialog != null)
                progressDialog.dismiss();
            if (result != null) {
                accessTokenToSend = result;
                makeLoginRequestToServer();
            } else {
            }
        }
    }

    protected void makeLoginRequestToServer() {
        // send data to server..wait for response
        // after success response, login user in app preferences and switch to login activity
        Intent returnIntent = new Intent();
        if (getIntent().hasExtra("wishlist_product_id"))
            returnIntent.putExtra("wishlist_product_id", getIntent().getExtras().getInt("wishlist_product_id"));
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
