package my.edu.tarc.profile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PROFILE_UPDATE_REQUEST = 123;
    public static final String PROFILE_NAME = "my.edu.tarc.profile.name";
    public static final String PROFILE_EMAIL = "my.edu.tarc.profile.email";
    private TextView textViewName, textViewEamil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName= (TextView)findViewById(R.id.textViewName);
        textViewEamil= (TextView)findViewById(R.id.textViewEmail);
    }
    public void updateProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, PROFILE_UPDATE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode = the unique code to indentify an intent call
        //resultCode = the result of an call; either OK or Cancel
        //data = the actual data returned by an intent call
        if(requestCode == PROFILE_UPDATE_REQUEST && resultCode == RESULT_OK){
            String name, email;
            name= data.getStringExtra(PROFILE_NAME);
            email = data.getStringExtra(PROFILE_EMAIL);
            textViewName.setText(getString(R.string.name) +": "+ name);
            textViewEamil.setText(getString(R.string.email)+": "+ email);
        }
    }

    public void visitBAIT2073(View view){
        String uri="http://bait2073.000webhostapp.com/welcome.html";
        Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
    public void showMain(View view){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }
    public void showDail(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+"0123456789"));
        startActivity(intent);
    }

    public void showSendTo(View v){
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"+
                "seekt@tarc.edu.my"));

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities =
                packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(intent);
        }
    }

}
