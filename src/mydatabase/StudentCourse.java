package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class StudentCourse {
    private Connection conn = Database.conn;
    public String student_id;
    public String course_name;
    public double score;
    /*返回结果栈类 参数第一个必选，第二个可选*/
    public Stack<StudentCourse> search(String student_id,String course_name){
        Stack<StudentCourse> stack = new Stack<StudentCourse>();
        try{
            String command;
            command="SELECT * FROM student_course WHERE student_id='"+student_id+"'";
            if(course_name.length() > 0){
                Course a=new Course();
                Stack<Course> tstack=a.search(course_name);
                String tc=" AND course_id="+tstack.pop().id;
                command=command+tc;
            }
            command=command+";";
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            conn.commit();
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()){
                StudentCourse t=new StudentCourse();
                t.student_id=rs.getString("student_id");
                try{
                    PreparedStatement tpStmt=null;
                    tpStmt=conn.prepareStatement("SELECT * FROM course WHERE course_id="+rs.getInt("course_id")+";");
                    conn.setAutoCommit(false);
                    conn.commit();
                    ResultSet trs = tpStmt.executeQuery();
                    trs.next();
                    t.course_name=trs.getString("course_name");
                    trs.close();
                    tpStmt.close();
                }catch(Exception e){
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                try{
                    t.score=rs.getDouble("score");
                }catch(Exception e){
                    // TODO 自动生成的 catch 块
                    System.out.println(t.student_id+" "+t.course_name+" 没有成绩！");
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

    /*返回操作在数据库中是否执行了  前两个参数都为必选,第三个参数可选，若score不选则置为0*/
    public boolean insert(String student_id,String course_name,double score){
        boolean result = false;
        try{
            Course a=new Course();
            Stack<Course> stack=a.search(course_name);

            String command;
            command="insert into student_course(student_id,course_id) values (?,?)";
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,student_id);
            pStmt.setInt(2,stack.pop().id);
            result = pStmt.executeUpdate() > 0;
            pStmt.close();
            conn.commit();

            if(score>0){
                StudentCourse tttemp=new StudentCourse();
                tttemp.update(student_id, course_name, score);
            }
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

    /*返回操作在数据库中是否执行了 两个参数皆为可选*/
    public boolean delete(String student_id,String course_name){
        boolean result = false;
        try{
            boolean pre=false;
            String command;
            command="DELETE FROM student_course";
            if(student_id.length() > 0){
                if(pre){
                    command=command+" AND ";
                }
                else{
                    command=command+" WHERE ";
                }
                String tc="student_id='"+student_id+"'";
                command=command+tc;
                pre=true;
            }
            if(course_name.length() > 0){
                if(pre){
                    command=command+" AND ";
                }
                else{
                    command=command+" WHERE ";
                }
                Course a=new Course();
                Stack<Course> tstack=a.search(course_name);
                String tc="course_id="+tstack.pop().id;
                command=command+tc;
                pre=true;
            }
            command=command+";";
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

    /*返回操作在数据库中是否执行了  三个参数都为必选，修改指定score*/
    public boolean update(String student_id,String course_name,double score){
        boolean result = false;
        try{
            Course a=new Course();
            Stack<Course> tstack=a.search(course_name);
            String command;
            command="UPDATE student_course SET score=? WHERE student_id=? AND course_id=?;";
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setDouble(1,score);
            pStmt.setString(2,student_id);
            pStmt.setInt(3,tstack.pop().id);
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
