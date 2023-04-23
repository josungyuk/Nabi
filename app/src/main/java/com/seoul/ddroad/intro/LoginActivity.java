package com.seoul.ddroad.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.seoul.ddroad.MainActivity;
import com.seoul.ddroad.R;

public class LoginActivity extends AppCompatActivity {

    SessionCallback callback;
    public static final String NICKNAME = "nick";
    public static final String USER_ID = "id";
    public static final String PROFILE_IMG = "img";
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                //로그아웃 성공시
            }
        });
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

        btn = (Button)findViewById(R.id.zxc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private class SessionCallback implements ISessionCallback {

        //세션 접속 성공 시
        @Override
        public void onSessionOpened() {

            UserManagement.requestMe(new MeResponseCallback() {

                //실패 시
                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    Logger.d(message);

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        finish();
                    } else {
                        //재 로그인 구현 필요
                        //redirectLoginActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    //로그인 성공 시 로그인한 사용자의 일련번호, 닉네임, 이미지url 리턴
                    //사용자 캐시 정보 업데이트 - 별 필요 없는듯
                    if (userProfile != null) {
                        userProfile.saveUserToCache();
                    }
                    Logger.e("succeeded to update user profile",userProfile,"\n");
                    //////////////////

                    final String nickName = userProfile.getNickname();//닉네임
                    final Long userID = userProfile.getId();//사용자 고유번호
                    final String profile = userProfile.getProfileImagePath();//사용자 프로필 경로
                    Log.e("UserProfile", userProfile.toString());//전체 정보 출력

                    final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(NICKNAME,nickName);
                    intent.putExtra(USER_ID,userID);
                    intent.putExtra(PROFILE_IMG,profile);

                    Toast.makeText(getApplicationContext(),nickName+"님 환영합니당",Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                    finish();

                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            // 세션 연결이 실패했을때
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }
}