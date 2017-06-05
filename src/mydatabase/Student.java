package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class Student {
    private Connection conn = Database.conn;
    public String id;
    public String name;
    public int year;
    public String sex;
    public String birthday;
    public String nation;
    public String class_name;
    public String dormitory_name;
    /*返回结果栈类 参数可选*/
    public Stack<Student> search(String id,String name){
        Stack<Student> stack = new Stack<Student>();
        try{
            boolean pre=false;
            String command;
            command="SELECT * FROM student";
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
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            conn.commit();
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()){
                Student t=new Student();
                t.id=rs.getString("student_id");
                t.name=rs.getString("student_name");
                t.year=rs.getInt("student_year");
                t.sex=rs.getString("student_sex");
                t.birthday=rs.getDate("student_birthday").toString();
                t.nation=rs.getString("student_nation");
                try{
                    PreparedStatement tpStmt=null;
                    tpStmt=conn.prepareStatement("SELECT * FROM class WHERE class_id="+rs.getInt("class_id")+";");
                    conn.setAutoCommit(false);
                    conn.commit();
                    ResultSet trs = tpStmt.executeQuery();
                    trs.next();
                    t.class_name=trs.getString("class_name");
                    trs.close();
                    tpStmt.close();
                }catch(Exception e){
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                try{
                    PreparedStatement tpStmt=null;
                    tpStmt=conn.prepareStatement("SELECT * FROM dormitory WHERE dormitory_id="+rs.getInt("dormitory_id")+";");
                    conn.setAutoCommit(false);
                    conn.commit();
                    ResultSet trs = tpStmt.executeQuery();
                    trs.next();
                    t.dormitory_name=trs.getString("dormitory_name");
                    trs.close();
                    tpStmt.close();
                }catch(Exception e){
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                stack.push(t);
            }

            rs.close();
            pStmt.close();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return stack;
    }

    /*返回操作在数据库中是否执行了  参数都为必选   birthday格式为"2015-01-01"  */
    public boolean insert(String id,String name,int year,String sex,String birthday,String nation,String class_name,String dormitory_name){
        boolean result = false;
        try{
            Class a=new Class();
            Stack<Class> stack=a.search(class_name);

            Dormitory d=new Dormitory();
            Stack<Dormitory> tstack=d.search(dormitory_name);

            java.sql.Date sqlDate=java.sql.Date.valueOf(birthday);
            String command;
            command="insert into student(student_id,student_name,student_year,student_sex,student_birthday,student_nation,class_id,dormitory_id) values (?,?,?,?,?,?,?,?)";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,id);
            pStmt.setString(2,name);
            pStmt.setInt(3,year);
            pStmt.setString(4,sex);
            pStmt.setDate(5,sqlDate);
            pStmt.setString(6,nation);
            pStmt.setInt(7,stack.pop().id);
            pStmt.setInt(8,tstack.pop().id);
            result = pStmt.executeUpdate() > 0;
            pStmt.close();
            conn.commit();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

    /*返回操作在数据库中是否执行了 参数必选*/
    public boolean delete(String name){
        boolean result = false;
        try{
            String command;
            command="DELETE FROM student WHERE student_name='"+name+"';";
            command=new String(command.getBytes(),"UTF-8");
            Statement stmt;
            stmt = conn.createStatement();
            result = stmt.executeUpdate(command) > 0;
            stmt.close();
            conn.commit();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

    /*返回操作在数据库中是否执行了  第一个参数为必选，其他随意但是至少要一个,year若为空值请置为0    */
    public boolean update(String name,String id,String toname,int year,String sex,String birthday,String nation){
        boolean result = false;
        try{
            boolean pre=false;
            String command;
            command="UPDATE student SET ";
            if(id.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="student_id='"+id+"'";
                command=command+tc;
                pre=true;
            }
            if(toname.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="student_name='"+toname+"'";
                command=command+tc;
                pre=true;
            }
            if(year>0){
                if(pre){
                    command=command+",";
                }
                String tc="student_year="+year;
                command=command+tc;
                pre=true;
            }
            if(sex.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="student_sex='"+sex+"'";
                command=command+tc;
                pre=true;
            }
            if(birthday.length() > 0){
                if(pre){
                    command=command+",";
                }
                java.sql.Date sqlDate=java.sql.Date.valueOf(birthday);
                String tc="student_birthday='"+sqlDate+"'";
                command=command+tc;
                pre=true;
            }
            if(nation.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="student_nation='"+nation+"'";
                command=command+tc;
                pre=true;
            }
            String tc=" WHERE student_name=?";
            command=command+tc;
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,name);
            result = pStmt.executeUpdate() > 0;
            pStmt.close();
            conn.commit();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

}
