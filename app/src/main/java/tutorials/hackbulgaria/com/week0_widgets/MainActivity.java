package tutorials.hackbulgaria.com.week0_widgets;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    private View coloredView;
    private EditText hexCodeEditText;
    private Button showColorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coloredView = findViewById(R.id.coloredView);
        hexCodeEditText = (EditText) findViewById(R.id.hexCodeEditText);
        showColorButton = (Button) findViewById(R.id.showColorButton);
    }


    @Override
    protected void onResume() {
        super.onResume();

        showColorButton.setOnClickListener(this);
        hexCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                changeColor(editable.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.showColorButton) {

            String hexCode = hexCodeEditText.getText().toString().trim();

            if (!isHexCodeEmpty(hexCode)) {

                changeColor(hexCode);
            }
        }
    }

    private void changeColor(String hexCode) {

        if (hexCode != null) {

            hexCodeEditText.setSelection(hexCode.length());

            if (!hexCode.startsWith("#")) {
                hexCode = "#" + hexCode;
                hexCodeEditText.setText(hexCode);
            }

            try {

                coloredView.setBackgroundColor(Color.parseColor(hexCode));

            } catch (IllegalArgumentException ex) {
                //Do Nothing
            }
        }
    }

    private boolean isHexCodeEmpty(String hexCode) {

        if (hexCode != null) {

            if (!hexCode.equals(""))
                return false;
        }

        Toast.makeText(this, getString(R.string.hex_code_required_message), Toast.LENGTH_SHORT).show();

        return true;
    }
}
