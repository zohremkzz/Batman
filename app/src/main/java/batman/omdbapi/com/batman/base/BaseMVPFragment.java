/*
    Copyright 2018 Gaurav Kumar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package batman.omdbapi.com.batman.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import batman.omdbapi.com.batman.R;
import batman.omdbapi.com.batman.di.component.ActivityComponent;
import batman.omdbapi.com.batman.utils.DialogUtils;
import batman.omdbapi.com.batman.utils.GeneralUtils;



public abstract class BaseMVPFragment<T> extends Fragment implements BaseContract.View<T> {

    protected Dialog progressDialog;
    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Set listeners and callback attached to the parent activity as null
     * in order to avoid activity leaking
     */

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Custom Progress Dialog with loading dots animation
     * @param message
     */
    @Override
    public void showProgressDialog(String title, @NonNull String message) {
        progressDialog = DialogUtils.createProgressDialog(getContext());
    }

    /**
     * Custom Progress Dialog with loading dots animation
     * @param resId
     */
    @Override
    public void showProgressDialog(@StringRes int resId) {
        String message = getString(resId);
        if(GeneralUtils.checkStringNotEmpty(message))
            showProgressDialog(null,message);
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showToastMessage(@NonNull String message) {
        if (GeneralUtils.checkStringNotEmpty(message))
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToastMessage(@StringRes int resId) {
        showToastMessage(getString(resId));
    }

    @Override
    public void showSnackBarMessage(@NonNull String message){
        if(GeneralUtils.checkStringNotEmpty(message))
            showSnackBar(message);
    }

    @Override
    public void showSnackBarMessage(@StringRes int resId){
        showSnackBarMessage(getString(resId));
    }

    /**
     * Creates a SnackBar for message display
     */
    private void showSnackBar(String message) {
        if(getActivity()!=null) {
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    message, Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView
                    .findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            snackbar.show();
        }
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.default_error_message));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    protected ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    protected abstract void initViews();

}
