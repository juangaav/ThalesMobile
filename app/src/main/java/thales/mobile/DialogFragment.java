package thales.mobile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import thales.mobile.model.Employee;
import thales.mobile.model.EmployeeSalaryCalculation;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DialogFragment extends androidx.fragment.app.DialogFragment implements EmployeeSalaryCalculation {

    public DialogFragment() {
        // Empty constructor is necessary
    }

    public static DialogFragment newInstance(Employee employee) {
        DialogFragment frag = new DialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("employee", employee);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView dialogEmployeeID = view.findViewById(R.id.dialog_employee_id_textview);
        TextView dialogEmployeeName = view.findViewById(R.id.dialog_employee_name_textview);
        TextView dialogEmployeeSalary = view.findViewById(R.id.dialog_employee_salary_textview);
        TextView dialogEmployeeAge = view.findViewById(R.id.dialog_employee_age_textview);
        Button dismissDialogButton = view.findViewById(R.id.dialog_ok_button);
        assert getArguments() != null;
        Employee employee = getArguments().getParcelable("employee");
        if(employee != null){
            dialogEmployeeID.setText(String.format("%s%s",
                    getString(R.string.recyclerview_employee_id_placeholder_text), employee.getId()));
            dialogEmployeeName.setText(String.format("%s%s",
                    getString(R.string.recyclerview_employee_name_placeholder_text), employee.getEmployeeName()));
            dialogEmployeeSalary.setText(String.format("%s%s",
                    getString(R.string.recyclerview_employee_salary_placeholder_text), calculateAnnualSalary(employee.getEmployeeSalary())));
            dialogEmployeeAge.setText(String.format("%s%s",
                    getString(R.string.recyclerview_employee_age_placeholder_text), employee.getEmployeeAge()));
        }

        dismissDialogButton.setOnClickListener(button -> Objects.requireNonNull(getDialog()).dismiss());
    }

    @Override
    public int calculateAnnualSalary(int salary) {
        return salary * 12;
    }
}