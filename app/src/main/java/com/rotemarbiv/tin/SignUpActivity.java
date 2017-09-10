package com.rotemarbiv.tin;

/**
 * Created by dafnaarbiv on 22/07/2017.
 */


        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.text.method.PasswordTransformationMethod;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.RadioButton;
        import android.widget.Toast;

        import com.rotemarbiv.tin.backend.BackendSimulator;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.InputStream;

/**
 * Created by laurescemama on 12/07/2017.
 */

public class SignUpActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    private static BackendSimulator backend = BackendSimulator.getInstance();

    public EditText fullName;
    public EditText password;
    public EditText dogName;
    public EditText address;
    public EditText phoneNumber;
    public EditText mail;

    public String dogSize;

    public Button signUpButton;
    public Button showPasswordButton;

//    private  static final int SELECTED_PICTURE = 1;
    public ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        fullName = (EditText) findViewById(R.id.fullNameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        dogName = (EditText) findViewById(R.id.dogNameInput);
        address = (EditText) findViewById(R.id.addressInput);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        mail = (EditText) findViewById(R.id.mailInput);
        profilePic = (ImageView) findViewById(R.id.profileImage);
        showPasswordButton = (Button) findViewById(R.id.showPasswordButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        showPasswordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toggles showing password

                if (password.getTransformationMethod() == null){
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }
                else {
                    password.setTransformationMethod(null);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String fullNameStr = fullName.getText().toString();
                String passwordStr = password.getText().toString();
                String dogNameStr = dogName.getText().toString();
                String addressStr = address.getText().toString();
                String phoneNumberStr = phoneNumber.getText().toString();
                String emailStr = mail.getText().toString();

                if (fullNameStr != null && fullNameStr.trim().length() > 0 &&
                        passwordStr != null && passwordStr.trim().length() > 0 &&
                        dogNameStr != null && dogNameStr.trim().length() > 0 &&
                        addressStr != null && addressStr.trim().length() > 0 &&
                        phoneNumberStr != null && phoneNumberStr.trim().length() > 0 &&
                        emailStr != null && emailStr.trim().length() > 0) {

                    com.rotemarbiv.tin.backend.User serverResponse = backend.signUp(fullNameStr, passwordStr,
                            dogNameStr, dogSize, addressStr, phoneNumberStr,
                            emailStr);
                    User self = User.convertBackendUserToUser(serverResponse);


//                    MyApp.mGlobalUsers.put(userNameStr, passwordStr); // TODO this is temp instead of the server

                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    intent.putExtra("selfUser", self);
                    startActivity(intent);
                }
                else{
                    // change toast to highlighting the missing inputs

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Fill all inputs in order to continue.", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.smallButton:
                if (checked)
                    dogSize = "S";
                break;
            case R.id.mediumButton:
                if (checked)
                    dogSize = "M";
                break;
            case R.id.largeButton:
                if (checked)
                    dogSize = "L";
                break;
        }

        Toast toast = Toast.makeText(getApplicationContext(),
                "Dog size is "+dogSize, Toast.LENGTH_SHORT);
        toast.show();

    }

//    public void choosePic(View view){
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, SELECTED_PICTURE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode){
//            case SELECTED_PICTURE:
//                if(requestCode == RESULT_OK){
//                    Uri uri = data.getData();
//                    String[]projection = {MediaStore.Images.Media.DATA};
//
//                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(projection[0]);
//                    String filePath = cursor.getString(columnIndex);
//                    cursor.close();
//
//                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
//                    Drawable d = new BitmapDrawable(yourSelectedImage);
//
//                    profilePic.setBackground(d);
//                }
//        }
//    }

    public void choosePic(View view){
        Intent photoPickIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickIntent.setDataAndType(data, "image/*");

        startActivityForResult(photoPickIntent, IMAGE_GALLERY_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST){
                // we came from Image Gallery
                Uri imageUri = data.getData();
//                stream to read the image data
                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);

                    profilePic.setImageBitmap(image);
                    //TODO give it to server

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}