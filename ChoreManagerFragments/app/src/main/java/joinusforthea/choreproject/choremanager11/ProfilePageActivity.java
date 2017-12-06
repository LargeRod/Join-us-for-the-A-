package joinusforthea.choreproject.choremanager11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfilePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
    }

    public void openMessages(View v) {
        Intent intent = new Intent(ProfilePageActivity.this, MessageActivity.class);
        startActivity(intent);
    }
}
