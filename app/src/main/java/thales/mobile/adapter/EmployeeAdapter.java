package thales.mobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thales.mobile.R;
import thales.mobile.model.Employee;
import thales.mobile.model.EmployeeSalaryCalculation;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeSearchResultHolder>
        implements EmployeeSalaryCalculation {
    private List<Employee> results = new ArrayList<>();
    private final Context context;

    public EmployeeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_recyclerview_item, parent, false);

        return new EmployeeSearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeSearchResultHolder holder, int position) {
        Employee employee = results.get(position);

        Resources res = context.getResources();
        holder.employeeIDTextView.setText(String.format("%s%s",
                res.getString(R.string.recyclerview_employee_id_placeholder_text), employee.getId()));
        holder.employeeNameTextView.setText(String.format("%s%s",
                res.getString(R.string.recyclerview_employee_name_placeholder_text), employee.getEmployeeName()));
        holder.employeeSalaryTextView.setText(String.format("%s%s",
                res.getString(R.string.recyclerview_employee_salary_placeholder_text), calculateAnnualSalary(employee.getEmployeeSalary())));
        holder.employeeAgeTextView.setText(String.format("%s%s",
                res.getString(R.string.recyclerview_employee_age_placeholder_text), employee.getEmployeeAge()));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setResults(List<Employee> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    @Override
    public int calculateAnnualSalary(int salary) {
        return salary * 12;
    }

    static class EmployeeSearchResultHolder extends RecyclerView.ViewHolder {
        private final TextView employeeIDTextView;
        private final TextView employeeNameTextView;
        private final TextView employeeSalaryTextView;
        private final TextView employeeAgeTextView;

        public EmployeeSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            employeeIDTextView = itemView.findViewById(R.id.employee_id_textview);
            employeeNameTextView = itemView.findViewById(R.id.employee_name_textview);
            employeeSalaryTextView = itemView.findViewById(R.id.employee_salary_textview);
            employeeAgeTextView = itemView.findViewById(R.id.employee_age_textview);
        }
    }
}
