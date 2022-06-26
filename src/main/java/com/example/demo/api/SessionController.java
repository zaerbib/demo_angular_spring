package com.example.demo.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.OperationResponse.ResponseStatusEnum;
import com.example.demo.model.session.SessionItem;
import com.example.demo.model.session.SessionResponse;
import com.example.demo.model.user.Login;
import com.example.demo.model.user.User;
import com.example.demo.repo.UserRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@Api(tags = {"Authentication"})
public class SessionController {
	
	@Autowired
	private UserRepo userRepo;
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Will return a security token, which must be passed in every request", response = SessionResponse.class) })
    @RequestMapping(value = "/session", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SessionResponse newSession(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
        User user = userRepo.findOneByUserIdAndPassword(login.getUsername(), login.getPassword()).orElse(null);
        SessionResponse resp = new SessionResponse();
        SessionItem sessionItem = new SessionItem();
        if (user != null){
            sessionItem.setToken("xxx.xxx.xxx");
            sessionItem.setUserId(user.getUserId());
            sessionItem.setFirstName(user.getFirstName());
            sessionItem.setLastName(user.getLastName());
            sessionItem.setEmail(user.getEmail());
            //sessionItem.setRole(user.getRole());

            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("Dummy Login Success");
            resp.setItem(sessionItem);
      }
      else{
            resp.setOperationStatus(ResponseStatusEnum.ERROR);
            resp.setOperationMessage("Login Failed");
      }
      return resp;
  }
}
