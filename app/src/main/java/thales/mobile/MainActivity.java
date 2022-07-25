package thales.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import thales.mobile.adapter.EmployeeAdapter;
import thales.mobile.model.Employee;
import thales.mobile.model.EmployeeAPIResponse;
import thales.mobile.model.SingleEmployeeAPIResponse;
import thales.mobile.viewmodel.EmployeeSearchViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private void initViews(){
        TextView employeeIDTextView = findViewById(R.id.input_employee_id_textview);
        Button employeeSearchButton = findViewById(R.id.button);

        EmployeeSearchViewModel viewModel =
                ViewModelProviders.of(this).get(EmployeeSearchViewModel.class);
        viewModel.init();

        employeeSearchButton.setOnClickListener(view -> {
            sendRequest(employeeIDTextView, viewModel);
            showData(viewModel, this);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void sendRequest(TextView employeeIDEditText, EmployeeSearchViewModel viewModel){
        String editTextValue = employeeIDEditText.getText().toString();
        if (!editTextValue.equals("")) {
            int employeeID = Integer.parseInt(editTextValue);
            viewModel.lookupEmployee(employeeID);
        }else{
            viewModel.getAllEmployees();
        }
    }

    private void showData(EmployeeSearchViewModel viewModel, Context context){
        viewModel.getEmployeeResponseLiveData().observe(this, employeeAPIResponse -> {
            if (employeeAPIResponse != null) {
                if(employeeAPIResponse.getStatus().equals("success")){
                    EmployeeAdapter adapter = new EmployeeAdapter(context);
                    ArrayList<Employee> employeeArrayList = (ArrayList<Employee>) employeeAPIResponse.getData();
                    adapter.setResults(employeeArrayList);
                    recyclerView = findViewById(R.id.employee_recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(adapter);
                }
            }else{
                Toast.makeText(MainActivity.this, context.getText(R.string.toast_error_api),
                        Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getSingleEmployeeResponseLiveData().observe(this, employeeAPIResponse -> {
            if (employeeAPIResponse != null) {
                if(employeeAPIResponse.getStatus().equals("success")){
                    Employee employee =  employeeAPIResponse.getData();
                    showFragmentDialog(employee);
                }
            }else{
                Toast.makeText(MainActivity.this, context.getText(R.string.toast_error_api),
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.init();
    }

    private void showFragmentDialog(Employee employee) {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialogFragment = DialogFragment.newInstance(employee);
        dialogFragment.show(fm, "fragment_edit_name");
    }

}