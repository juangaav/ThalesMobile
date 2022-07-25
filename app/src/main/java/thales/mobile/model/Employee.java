package thales.mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee implements Parcelable {
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("employee_name")
        @Expose
        String employee_name;

        @SerializedName("employee_salary")
        @Expose
        int employee_salary;

        @SerializedName("employee_age")
        @Expose
        int employee_age;
        String profile_image;

        protected Employee(Parcel in) {
                id = in.readInt();
                employee_name = in.readString();
                employee_salary = in.readInt();
                employee_age = in.readInt();
                profile_image = in.readString();
        }

        public static final Creator<Employee> CREATOR = new Creator<Employee>() {
                @Override
                public Employee createFromParcel(Parcel in) {
                        return new Employee(in);
                }

                @Override
                public Employee[] newArray(int size) {
                        return new Employee[size];
                }
        };

        public int getId() {
                return id;
        }

        public String getEmployeeName() {
                return employee_name;
        }

        public int getEmployeeSalary() {
                return employee_salary;
        }

        public int getEmployeeAge() {
                return employee_age;
        }

        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(id);
                parcel.writeString(employee_name);
                parcel.writeInt(employee_salary);
                parcel.writeInt(employee_age);
                parcel.writeString(profile_image);
        }
}
