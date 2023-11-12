package com.example.realone;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button generatePasswordButton = findViewById(R.id.generatePasswordButton);
        Button createNewButton = findViewById(R.id.generateNew);

        createNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNew();
            }
        });

        generatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateAndDisplayPassword();
            }
        });
    }

    private void generateAndDisplayPassword() {
        String generatedPassword = generatePassword();

        TextView passwordResult = new TextView(this);
        passwordResult.setText("Wygenerowane hasło: " + generatedPassword);
        passwordResult.setPadding(0, 16, 0, 0);

        TextView passwordResults = findViewById(R.id.passwordResult);
        passwordResults.setText("Wygenerowane hasło: " + generatedPassword);
    }

    private String generatePassword() {
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String digitChars = "0123456789";
        String specialChars = "!@#$%^&*()_-+=<>?";
        EditText passwordLengthInput = findViewById(R.id.passwordLengthInput);
        CheckBox checkBoxLetters = findViewById(R.id.checkBoxLetters);
        CheckBox checkBoxDigits = findViewById(R.id.checkBoxDigits);
        CheckBox checkBoxSpecialChars = findViewById(R.id.checkBoxSpecialChars);

        int passwordLength = Integer.parseInt(passwordLengthInput.getText().toString());
        boolean includeLetters = checkBoxLetters.isChecked();
        boolean includeDigits = checkBoxDigits.isChecked();
        boolean includeSpecialChars = checkBoxSpecialChars.isChecked();

        StringBuilder allowedChars = new StringBuilder();

        if (includeLetters) {
            allowedChars.append(upperChars).append(lowerChars);
        }
        if (includeDigits) {
            allowedChars.append(digitChars);
        }
        if (includeSpecialChars) {
            allowedChars.append(specialChars);
        }

        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(randomIndex));
        }

        return password.toString();
    }
    private void generateNew(){
        EditText name = findViewById(R.id.imie);
        EditText nazwisko = findViewById(R.id.nazwisko);
//        Spinner selectStanowisko = findViewById(R.id.selectStanowisko);
        TextView passwordResult = findViewById(R.id.passwordResult);

        String firstName = name.getText().toString();
        String lastName = nazwisko.getText().toString();
//        String stanowisko = selectStanowisko.getSelectedItem().toString();
        String generatedPassword = generatePassword();

        TextView displayResult = new TextView(this);
        displayResult.setText("Dane pracownika: " + firstName + " " + lastName + " " + "tester" +
                "\nHasło: " + generatedPassword);
        displayResult.setPadding(0, 16, 0, 0);

        TextView passwordResults = findViewById(R.id.passwordResult);
        passwordResults.setText("Dane pracownika: " + firstName + " " + lastName + " " + "Tester" +
                "\nHasło: " + generatedPassword);;

    }
}
