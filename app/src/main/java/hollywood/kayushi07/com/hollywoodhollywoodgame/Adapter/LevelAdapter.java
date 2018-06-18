//package hollywood.kayushi07.com.hollywoodhollywoodgame.Adapter;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AlertDialog;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.List;
//
//import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.Level;
//import hollywood.kayushi07.com.hollywoodhollywoodgame.R;
//
///**
// * Created by Belal on 9/30/2017.
// */
//
//public class LevelAdapter extends ArrayAdapter<Level> {
//
//    Context mCtx;
//    int listLayoutRes;
//    List<Level> employeeList;
//    SQLiteDatabase mDatabase;
//
//    public LevelAdapter(Context mCtx, int listLayoutRes, List<Level> employeeList, SQLiteDatabase mDatabase) {
//        super(mCtx, listLayoutRes, employeeList);
//
//        this.mCtx = mCtx;
//        this.listLayoutRes = listLayoutRes;
//        this.employeeList = employeeList;
//        this.mDatabase = mDatabase;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        View view = inflater.inflate(listLayoutRes, null);
//
//        final Level employee = employeeList.get(position);
////
////
////        TextView textViewName = view.findViewById(R.id.textViewName);
////        TextView textViewDept = view.findViewById(R.id.textViewDepartment);
////        TextView textViewSalary = view.findViewById(R.id.textViewSalary);
////        TextView textViewJoiningDate = view.findViewById(R.id.textViewJoiningDate);
//
//
//        textViewName.setText(employee.getName());
//        textViewDept.setText(employee.getDept());
//        textViewSalary.setText(String.valueOf(employee.getSalary()));
//        textViewJoiningDate.setText(employee.getJoiningDate());
//
//
//        //adding a clicklistener to button
//        updateEmployee(employee);
//
//
//        return view;
//    }
//
//    private void updateEmployee(final Employee employee) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
//
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        View view = inflater.inflate(R.layout.dialog_update_employee, null);
//        builder.setView(view);
//
//
//        final EditText editTextName = view.findViewById(R.id.editTextName);
//        final EditText editTextSalary = view.findViewById(R.id.editTextSalary);
//        final Spinner spinnerDepartment = view.findViewById(R.id.spinnerDepartment);
//
//        editTextName.setText(employee.getName());
//        editTextSalary.setText(String.valueOf(employee.getSalary()));
//
//        final AlertDialog dialog = builder.create();
//        dialog.show();
//
//        view.findViewById(R.id.buttonUpdateEmployee).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editTextName.getText().toString().trim();
//                String salary = editTextSalary.getText().toString().trim();
//                String dept = spinnerDepartment.getSelectedItem().toString();
//
//
//                String sql = "UPDATE employees \n" +
//                        "SET name = ?, \n" +
//                        "department = ?, \n" +
//                        "salary = ? \n" +
//                        "WHERE id = ?;\n";
//
//                mDatabase.execSQL(sql, new String[]{name, dept, salary, String.valueOf(employee.getId())});
//                Toast.makeText(mCtx, "Employee Updated", Toast.LENGTH_SHORT).show();
//                reloadEmployeesFromDatabase();
//
//                dialog.dismiss();
//            }
//        });
//    }
//
//    private void reloadEmployeesFromDatabase() {
//        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM employees", null);
//        if (cursorEmployees.moveToFirst()) {
//            employeeList.clear();
//            do {
//                employeeList.add(new Employee(
//                        cursorEmployees.getInt(0),
//                        cursorEmployees.getString(1),
//                        cursorEmployees.getString(2),
//                        cursorEmployees.getString(3),
//                        cursorEmployees.getDouble(4)
//                ));
//            } while (cursorEmployees.moveToNext());
//        }
//        cursorEmployees.close();
//        notifyDataSetChanged();
//    }
//
//}
