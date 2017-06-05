package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class Dormitory {
    private Connection conn = Database.conn;
    public int id;
    public String name;
    /*返回结果栈类 参数可选*/
    public Stack<Dormitory> search(String name){
        Stack<Dormitory> stack = new Stack<Dormitory>();
        try{
            String command;
            command="SELECT * FROM dormitory";
            if(name.length() > 0){
                String tc=" WHERE dormitory_name='"+name+"'";
                command=command+tc;
            }
            command=command+";";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            conn.commit();
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()){
                Dormitory t=new Dormitory();
                t.id=rs.getInt("dormitory_id");
                t.name=rs.getString("dormitory_name");
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
    public boolean insert(String name){
        boolean result = false;
        try{
            String command;
            command="insert into dormitory(dormitory_name) values (?)";
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

    /*返回操作在数据库中是否执行了 参数必选*/
    public boolean delete(String name){
        boolean result = false;
        try{
            String command;
            command="DELETE FROM dormitory WHERE dormitory_name='"+name+"';";
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
    public boolean update(String name,String toname){
        boolean result = false;
        try{
            String command;
            command="UPDATE dormitory SET dormitory_name=? WHERE dormitory_name=?";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,toname);
            pStmt.setString(2,name);
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
