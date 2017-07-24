package xyz.jeevan.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.jeevan.api.domain.User;
import xyz.jeevan.api.service.user.UserService;
import xyz.jeevan.api.utils.APIEndpoints;
import xyz.jeevan.api.utils.AppConstants;

@RestController
@Api(value = "users")
@RequestMapping(value = APIEndpoints.USER_API_URL)
public class UserController extends BaseController {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @ApiOperation(value = "Create an user.", notes = "API to create new user.",
      response = User.class)
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> create(@RequestBody User user) {
    LOG.info("Saving new user with email {}.", user.getEmail());
    userService.create(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Fetch user by id.", notes = "API to fetch user by id.",
      response = User.class)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getById(@PathVariable String id,
      @RequestParam(value = AppConstants.FIELDS, required = false) String fields) {
    LOG.info("Fetch user by id {}", id);
    return new ResponseEntity<User>(limitDataFields(userService.getById(id), User.class, fields),
        HttpStatus.MULTI_STATUS.OK);
  }
}
