package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class Teacher {
    private Connection conn = Database.conn;
    public String id;
    public String name;
    public String sex;
    public String academy_name;
    /*返回结果栈类 参数可选*/
    public Stack<Teacher> search(String id,String name){
        Stack<Teacher> stack = new Stack<Teacher>();
        try{
            boolean pre=false;
            String command;
            command="SELECT * FROM teacher";
            if(id.length() > 0){
                if(pre){
                    command=command+" AND ";
                }
                else{
                    command=command+" WHERE ";
                }
                String tc="teacher_id='"+id+"'";
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
                String tc="teacher_name='"+name+"'";
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
                Teacher t=new Teacher();
                t.id=rs.getString("teacher_id");
                t.name=rs.getString("teacher_name");
                t.sex=rs.getString("teacher_sex");
                try{
                    PreparedStatement tpStmt=null;
                    tpStmt=conn.prepareStatement("SELECT * FROM academy WHERE academy_id="+rs.getInt("academy_id")+";");
                    conn.setAutoCommit(false);
                    conn.commit();
                    ResultSet trs = tpStmt.executeQuery();
                    trs.next();
                    t.academy_name=trs.getString("academy_name");
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

    /*返回操作在数据库中是否执行了  两个参数都为必选*/
    public boolean insert(String id,String name,String sex,String academy_name){
        boolean result = false;
        try{
            Academy a=new Academy();
            Stack<Academy> stack=a.search(academy_name,"");

            String command;
            command="insert into teacher(teacher_id,teacher_name,teacher_sex,academy_id) values (?,?,?,?)";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,id);
            pStmt.setString(2,name);
            pStmt.setString(3,sex);
            pStmt.setInt(4,stack.pop().id);
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
            command="DELETE FROM teacher WHERE teacher_name='"+name+"';";
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

    /*返回操作在数据库中是否执行了  两个参数都为必选，将名字修改为另一个名字*/
    public boolean update(String name,String id,String toname,String sex){
        boolean result = false;
        try{
            boolean pre=false;
            String command;
            command="UPDATE teacher SET ";
            if(id.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="teacher_id='"+id+"'";
                command=command+tc;
                pre=true;
            }
            if(toname.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="teacher_name='"+toname+"'";
                command=command+tc;
                pre=true;
            }
            if(sex.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="teacher_sex='"+sex+"'";
                command=command+tc;
                pre=true;
            }
            String tc=" WHERE teacher_name=?";
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
