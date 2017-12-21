package com.myStyle.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myStyle.dao.UserDao;

@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService  {
    
	@Autowired
	private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	com.myStyle.entity.User users=userDao.querySingleUser1(username);
		if  (users==null)  
            throw new UsernameNotFoundException(username+" not exist!");  
		//List<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
		List<GrantedAuthority> grantedAuths = null;
		// 封装成spring security的user
				User userdetail = new User(
						users.getUserName(), 
						users.getPassWord(),
						true, 
						true, 
						true,
						true, 
						grantedAuths	//用户的权限
					);
		return userdetail;
    }
    
 // 取得用户的权限
 	/*private List<GrantedAuthority> obtionGrantedAuthorities(com.myStyle.entity.User user) {
 		List<GrantedAuthority> result=new ArrayList<GrantedAuthority>();
 		result.add(new SimpleGrantedAuthority(user.getRole()));
 		return result;
 	}*/
    
   /* private List<GrantedAuthority> buildGrantedAuthorities(Role role){
    	
        List<GrantedAuthority>  result=new ArrayList<GrantedAuthority>();
        
        result.add(new SimpleGrantedAuthority(role.getName()));
        return result;
    }
    
    private User buildUserForAuthentication(AdminUser user,List<GrantedAuthority> authorities){
        String username=user.getName();
        String password=user.getPsw();
        User u= new User(username, password, authorities);
        //System.out.println(u.getUsername()+"  "+u.getPassword()+"   "+u.getAuthorities());
        return u;
    	//return null;
    }*/
    
}