package com.chen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chen.entity.Part;
import com.chen.entity.Product;
import com.chen.entity.Role_Menu;
import com.chen.entity.User;
import com.chen.entity.UserWorkPart;
import com.chen.entity.User_Role;
import com.chen.entity.Work;
import com.chen.entity.complation;
import com.chen.entity.ls_menu;
import com.chen.utils.DataBaseUtil;

public class ControlDaoImpl extends BaseDao implements ControlDao{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	@Override
	public User login(String loginname, String password) {
		conn = getConnection();
		User user = new User();
		String sql = "SELECT * FROM easybuy_user where loginName='" + loginname + "' and password='" + password + "'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String userName = rs.getString("userName");
					String loginName = rs.getString("loginName");
					String password1 = rs.getString("password");
					int sex = rs.getInt("sex");
					String identityCode = rs.getString("identityCode");
					String email = rs.getString("email");
					String mobile = rs.getString("mobile");
					int type=rs.getInt("type");
					return new User(id, userName, loginName, password1, sex, identityCode, email, mobile,type);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, ps, conn);
		}

		return user;
	}
	@Override
	public List<ls_menu> SelectAll(int userId) {
		conn = getConnection();
		List<ls_menu> list = null;
		String sql = "SELECT `id`,`mname`,`mfatherid`,`type`,`url`,`button` FROM `ls_menu`WHERE id IN (SELECT `menuid` FROM `user_menu` WHERE `userid`='"
				+ userId + "')";
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<ls_menu>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String userName = rs.getString("mname");
					int fatherId = rs.getInt("mfatherid");
					int typeId = rs.getInt("type");
					String url = rs.getString("url");
					String button = rs.getString("button");
					list.add(new ls_menu(id, userName, fatherId, typeId, url, button));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, ps, conn);
		}

		return list;

	}
	@Override
	public List<ls_menu> SelectContext(int fatherId) {
		conn = getConnection();
		List<ls_menu> list = null;
		String sql = "SELECT `id`,`mname`,`mfatherid`,`type`,`url`,`button` FROM `ls_menu` WHERE  `mfatherid` ='"
				+ fatherId + "'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<ls_menu>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String mname = rs.getString("mname");
					int fatherId1 = rs.getInt("mfatherid");
					int typeId = rs.getInt("type");
					String url = rs.getString("url");
					String button = rs.getString("button");
					list.add(new ls_menu(id, mname, fatherId1, typeId, url, button));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, ps, conn);
		}
		return list;
	}
	@Override
	public List<ls_menu> SelectMenu(String name, int startIndex, int maxlength) {
		List<ls_menu> list = null;
		conn = getConnection();
		String sql = "SELECT `id`,`mname`,`mfatherid`,`type`,`url`,`button` FROM `ls_menu` WHERE 1=1\n";
		try {
			if (name != null && name.length() > 0) {
				sql += " AND `mname` LIKE CONCAT('%','" + name + "','%')\n";

			}
			//sql += "LIMIT " + startIndex + "," + maxlength + "";
			
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<ls_menu>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String mname = rs.getString("mname");
					int mfatherid = rs.getInt("mfatherid");

					int type = rs.getInt("type");
					String url = rs.getString("url");
					String button = rs.getString("button");
					list.add(new ls_menu(id, mname, mfatherid, type, url, button));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int countMenu(String name) {
		conn = getConnection();
		String sql = "SELECT COUNT(1) FROM `ls_menu` where 1=1\n ";
		int num = 0;
		try {
			
			if (name != null && name.length() > 0) {
				sql += " AND `mname` LIKE CONCAT('%','" + name + "','%')\n";

			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					num = rs.getInt("COUNT(1)");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, ps, conn);
		}

		return num;
	}
	@Override
	public int addMenu(ls_menu menu) {
		String sql="INSERT INTO `ls_menu`(`mname`,`mfatherid`,`type`,`url`,`button`)VALUES(?,?,?,?,?)";
		Object []params= {menu.getMname(),menu.getMfatherid(),menu.getType(),menu.getUrl(),menu.getButton()};
		return executeUpdate(sql, params);
	}
	@Override
	public int delMenu(int id) {
		String sql="DELETE FROM `ls_menu` WHERE id=?";
		Object []params= {id};
		return executeUpdate(sql, params);
	}
	@Override
	public int upMenu(ls_menu menu) {
		String sql="UPDATE `ls_menu` SET `mname`=?,`mfatherid`=?,`type`=?,`url`=?,`button`=? WHERE `id`=?";
		Object []params= {menu.getMname(),menu.getMfatherid(),menu.getType(),menu.getUrl(),menu.getButton(),menu.getId()};
		return executeUpdate(sql, params);
	}
	@Override
	public List<ls_menu> SelectMenuOne(int id) {
		List<ls_menu> list = null;
		conn = getConnection();
		String sql = "SELECT `id`,`mname`,`mfatherid`,`type`,`url`,`button` FROM `ls_menu` WHERE `id`="+id+"";
		try {
			
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<ls_menu>();
				while (rs.next()) {
					int id1 = rs.getInt("id");
					String mname = rs.getString("mname");
					int mfatherid = rs.getInt("mfatherid");

					int type = rs.getInt("type");
					String url = rs.getString("url");
					String button = rs.getString("button");
					list.add(new ls_menu(id1, mname, mfatherid, type, url, button));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<ls_menu> SelectBtn(Integer typeId) {
		List<ls_menu> list = null;
		conn = getConnection();
		String sql = "SELECT `id`,`mname`,`mfatherid`,`type`,`url`,`button` FROM `ls_menu` WHERE `type`="+typeId+"";
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<ls_menu>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String mname = rs.getString("mname");
					int mfatherid = rs.getInt("mfatherid");

					int type = rs.getInt("type");
					String url = rs.getString("url");
					String button = rs.getString("button");
					list.add(new ls_menu(id, mname, mfatherid, type, url, button));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<complation> SelectUserList(String name, int startIndex, int maxlength) {
		conn=getConnection();
		List<complation> list = null;
		String sql="SELECT `easybuy_user`.`id`,`userName`,`loginName`,`rname`,`sex`,`partName`,`workName`,`email`,`mobile`,`type` FROM `easybuy_user`,`ls_role`,`work`,`part`,`work_part_user`,`user_role` WHERE `uId`=`easybuy_user`.`id` AND `wId`=`work`.`id` AND `pId`=`part`.`id` AND `ls_role`.`id`=`user_role`.`roleid` AND `user_role`.`userid` =`easybuy_user`.`id`";
          try {
        	  if (name != null && name.length() > 0) {
  				sql += " AND `userName` LIKE CONCAT('%','" + name + "','%')\n";

  			}
        	  sql += "LIMIT " + startIndex + "," + maxlength + "";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<complation>();
				while (rs.next()) {
					complation use=new complation();
					 use.setId(rs.getInt(1));
					use.setUserName(rs.getString(2));
					use.setLoginName(rs.getString(3));
					use.setRoleName(rs.getString(4));
					use.setSex(rs.getInt(5));
					use.setPartName(rs.getString(6)); 
					use.setWorkName( rs.getString(7));
					use.setEmail(rs.getString(8));
					use.setMobile(rs.getString(9));
					use.setType(rs.getInt(10));
					
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<User_Role> SelectUserRole(String name, int startIndex, int maxlength) {
		conn=getConnection();
		List<User_Role>list=null;
		String sql="SELECT `ls_role`.`id`,`userName`,`loginName`,`sex`,`email`,`mobile`,`type`,`rname` FROM `ls_role`,`easybuy_user`,`user_role`WHERE  `user_role`.`userid`=`easybuy_user`.`id` AND `ls_role`.`id`=`user_role`.`roleid`";
		try {
      	  if (name != null && name.length() > 0) {
				sql += " AND `userName` LIKE CONCAT('%','" + name + "','%')\n";

			}
      	sql += "LIMIT " + startIndex + "," + maxlength + "";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<User_Role>();
				while (rs.next()) {
					User_Role use=new User_Role();
					 use.setId(rs.getInt(1));
					use.setUserName(rs.getString(2));
					use.setLoginName(rs.getString(3));
					use.setSex(rs.getInt(4));
					use.setEmail(rs.getString(5));
					use.setMobile(rs.getString(6));
					use.setType(rs.getInt(7));
					use.setRname(rs.getString(8));
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int countUser(String name) {
		conn = getConnection();
		String sql = "SELECT COUNT(1) FROM `easybuy_user` where 1=1\n ";
		int num = 0;
		try {
			
			if (name != null && name.length() > 0) {
				sql += " AND `mname` LIKE CONCAT('%','" + name + "','%')\n";

			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					num = rs.getInt("COUNT(1)");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, ps, conn);
		}

		return num;
	}
	@Override
	public int addUser(User use) {
		String sql="INSERT INTO `easybuy_user`(`userName`,`loginName`,`password`,`sex`,`email`,`mobile`,`type`)VALUES(?,?,?,?,?,?,?)";
		Object []params= {use.getUserName(),use.getLoginName(),use.getPassword(),use.getSex(),use.getEmail(),use.getMobile(),use.getType()};
		return executeUpdate(sql, params);
	}
	@Override
	public int addUserWork(int userId,UserWorkPart uwp) {
		String sql="INSERT INTO `work_part_user`(`uId`,`wId`,`pId`)VALUES(?,?,?)";
		Object []params= {userId,uwp.getwId(),uwp.getpId()};
		return executeUpdate(sql, params);
	}
	@Override
	public int addUserRole(int userId,User_Role ur) {
		String sql="INSERT INTO `user_role`(`userid`,`roleid`)VALUES(?,?)";
		Object []params= {userId,ur.getId()};
		return executeUpdate(sql, params);
	}
	@Override
	public int addRole(User_Role ur) {
		String sql="INSERT INTO `ls_role`(`rname`)VALUES(?)";
		Object []params= {ur.getRname()};
		return executeUpdate(sql, params);
	}
	@Override
	public int delRole(int id) {
		String sql="DELETE `ls_role`WHERE id=?";
		Object []params= {id};
		return executeUpdate(sql, params);
	}
	@Override
	public int upRole(User_Role ur) {
		String sql="UPDATE `ls_role` SET `rname`=? WHERE id=?";
		Object []params= {ur.getRname(),ur.getId()};
		return executeUpdate(sql, params);
	}
	@Override
	public List<User_Role> SelectRoleOne(int id) {
		conn=getConnection();
		List<User_Role>list=null;
		String sql="SELECT `rname` FROM `ls_role` WHERE `id`="+id+"";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<User_Role>();
				while (rs.next()) {
					User_Role use=new User_Role();	
					use.setRname(rs.getString(1));
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public User userByid(String name) {
		conn = getConnection();
	
		User user = null;
		String sql = "SELECT `id`,`userName`,`loginName`,`password`,`sex`,`identityCode`,`email`,`mobile`,`type`FROM `easybuy_user` where userName='"
				+ name + "'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				
				while (rs.next()) {
					int id = rs.getInt("id");
					String uname = rs.getString("userName");
					String loginname = rs.getString("loginName");
					String password1 = rs.getString("password");
					int sex1 = rs.getInt("sex");
					String card = rs.getString("identityCode");
					String email = rs.getString("email");
					String mobile = rs.getString("mobile");
					int type=rs.getInt("type");
					return new User(id, uname, loginname, password1, sex1, card, email, mobile,type);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}
	@Override
	public List<User_Role> SelectBtn() {
		conn=getConnection();
		List<User_Role> list = null;
		String sql="SELECT `id`,`rname` FROM `ls_role` ";
          try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<User_Role>();
				while (rs.next()) {
					User_Role use=new User_Role();
					 use.setId(rs.getInt(1));
					 use.setRname(rs.getString(2));
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int delUser(int id) {
		String sql="DELETE FROM `easybuy_user` WHERE id=?";
		Object []params= {id};
		return executeUpdate(sql, params);
	}
	@Override
	public int upUser(User user) {
		String sql="UPDATE `easybuy_user` SET `userName`=?,`loginName`=?,`password`=?,`sex`=?,`email`=?,`mobile`=?,`type`=? WHERE `id`=?";
		Object []params= {user.getUserName(),user.getLoginName(),user.getPassword(),user.getSex(),user.getEmail(),user.getMobile(),user.getType(),user.getId()};
		return executeUpdate(sql, params);
	}
	@Override
	public int upPart(int userId, UserWorkPart uwp) {
		String sql="UPDATE `work_part_user` SET `wId`=?,`pId`=? WHERE `uId`=?";
		Object []params= {uwp.getwId(),uwp.getpId(),userId};
		return executeUpdate(sql, params);
	}
	@Override
	public int upUserRole(int userId,User_Role ur) {
		String sql="UPDATE `user_role` SET `roleid`=? WHERE `userid`=?";
		Object []params= {ur.getId(),userId};
		return executeUpdate(sql, params);
	}
	@Override
	public List<complation> selectUserOne(int id) {
		conn=getConnection();
		List<complation> list = null;
		String sql="SELECT `easybuy_user`.`id`,`userName`,`password`,`loginName`,`rname`,`sex`,`partName`,`workName`,`email`,`mobile`,`type` FROM `easybuy_user`,`ls_role`,`work`,`part`,`work_part_user`,`user_role` WHERE `uId`=`easybuy_user`.`id` AND `wId`=`work`.`id` AND `pId`=`part`.`id` AND `ls_role`.`id`=`user_role`.`roleid` AND `user_role`.`userid` =`easybuy_user`.`id` AND `easybuy_user`.`id`="+id+"";
          try {
        	  
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<complation>();
				while (rs.next()) {
					complation use=new complation();
					 use.setId(rs.getInt(1));
					use.setUserName(rs.getString(2));
					use.setPassword(rs.getString(3));
					use.setLoginName(rs.getString(4));
					
					use.setRoleName(rs.getString(5));
					use.setSex(rs.getInt(6));
					use.setPartName(rs.getString(7)); 
					use.setWorkName( rs.getString(8));
					use.setEmail(rs.getString(9));
					use.setMobile(rs.getString(10));
					use.setType(rs.getInt(11));
					
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int delUserByid(int roleId) {
		String sql="DELETE FROM `menu_role` WHERE `rId`=?";
		Object[]obj= {roleId};
		return executeUpdate(sql, obj);
	}
	@Override
	public List<ls_menu> selectAll() {
		List<ls_menu> list = null;
		conn = getConnection();
		String sql = "SELECT `id`,`mname`,`mfatherid`,`type`,`url`,`button` FROM `ls_menu`";
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<ls_menu>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String mname = rs.getString("mname");
					int mfatherid = rs.getInt("mfatherid");

					int type = rs.getInt("type");
					String url = rs.getString("url");
					String button = rs.getString("button");
					list.add(new ls_menu(id, mname, mfatherid, type, url, button));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<ls_menu> roleByUserId(int roleId) {
		List<ls_menu> list = null;
		conn = getConnection();
		String sql = "SELECT * FROM `ls_menu` WHERE id IN (SELECT `mId` FROM `menu_role` WHERE `rId`="+roleId+")";
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<ls_menu>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String mname = rs.getString("mname");
					int mfatherid = rs.getInt("mfatherid");

					int type = rs.getInt("type");
					String url = rs.getString("url");
					String button = rs.getString("button");
					list.add(new ls_menu(id, mname, mfatherid, type, url, button));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int addRoleByUserid(int menuId, int roleId) {//分配权限 {
		String sql="INSERT INTO `menu_role`VALUES(?,?)";
		Object[]obj= {roleId,menuId};
		return executeUpdate(sql, obj);
	}
	@Override
	public List<User_Role> selectUserRole() {
		conn=getConnection();
		List<User_Role> list = null;
		String sql="SELECT `id`,`rname` FROM `ls_role` ";
          try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<User_Role>();
				while (rs.next()) {
					User_Role use=new User_Role();
					 use.setId(rs.getInt(1));
					 use.setRname(rs.getString(2));
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int selectRolByUid(int uid) {
		 conn=getConnection();
			PreparedStatement prs=null;
			try {
				prs=conn.prepareStatement("SELECT `userid`,`roleid` FROM `user_role` where `userid`=?");
				prs.setInt(1, uid);
			    rs=prs.executeQuery();
				return rs.next()?rs.getInt(2):0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}
	@Override
	public List<Part> selectPart() {
		conn=getConnection();
		List<Part> list = null;
		String sql="SELECT `id`,`partName` FROM `part`  ";
          try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<Part>();
				while (rs.next()) {
					Part use=new Part();
					 use.setId(rs.getInt(1));
					 use.setName(rs.getString(2));
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int  selectWorkByuid(int uid) {
		 conn=getConnection();
			PreparedStatement prs=null;
			try {
				prs=conn.prepareStatement("SELECT `uId`,`wId`FROM `work_part_user` WHERE `uId`="+uid+"");
				  rs=prs.executeQuery();
				return rs.next()?rs.getInt(2):0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}
	@Override
	public int selectPartByuid(int uid) {
		conn=getConnection();
		PreparedStatement prs=null;
		try {
			prs=conn.prepareStatement("SELECT `uId`,`pId` FROM `work_part_user` WHERE `uId`="+uid+"");
			
		    rs=prs.executeQuery();
			return rs.next()?rs.getInt(2):0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<Work> selectWork() {
		conn=getConnection();
		List<Work> list = null;
		String sql="SELECT `id`,`workName` FROM `work` ";
          try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<Work>();
				while (rs.next()) {
					Work use=new Work();
					 use.setId(rs.getInt(1));
					 use.setName(rs.getString(2));
					list.add(use);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Product> selecProducts() {
		List<Product> list = null;
		conn = getConnection();
		String sql = "SELECT `id`,`name`,`price`FROM `easybuy_product`\n";
		try {
			
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs != null) {
				list = new ArrayList<Product>();
				while (rs.next()) {
					Product prod=new Product();
					prod.setId(rs.getInt(1));
					prod.setName(rs.getString(2));
					prod.setPrice(rs.getShort(3));
					list.add(prod);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

}
