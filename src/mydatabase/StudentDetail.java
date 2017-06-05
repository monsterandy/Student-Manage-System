package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Stack;

public class StudentDetail {
    private Connection conn = Database.conn;
    public String id;
    public String name;
    public String sex;
    public String birthday;
    public String nation;
    public String dormitory_name;
    public String academy_name;
    public String major_name;
    public String class_name;
    /*返回结果栈类 参数可选*/
    public Stack<StudentDetail> search(String id,String name){
        Stack<StudentDetail> stack = new Stack<StudentDetail>();
        try{
            boolean pre=false;
            String command;
            command="SELECT * FROM student_detail";
            if(id.length() > 0){
                if(pre){
                    command=command+" AND ";
                }
                else{
                    command=command+" WHERE ";
                }
                String tc="student_id='"+id+"'";
                command=command+tc;
                pre=true;
            }
            if(name.length() > 0){
                if(pre){
                    command=command+" AND ";
                }
                else{
                    command=command+" WHERE ";
                }
                String tc="student_name='"+name+"'";
                command=command+tc;
                pre=true;
            }
            command=command+";";
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            conn.commit();
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()){
                StudentDetail t=new StudentDetail();
                t.id=rs.getString("student_id");
                t.name=rs.getString("student_name");
                t.sex=rs.getString("student_sex");
                t.birthday=rs.getDate("student_birthday").toString();
                t.nation=rs.getString("student_nation");
                t.dormitory_name=rs.getString("dormitory_name");
                t.academy_name=rs.getString("academy_name");
                t.major_name=rs.getString("major_name");
                t.class_name=rs.getString("class_name");
                stack.push(t);
            }

            rs.close();
            pStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stack;
    }

}
