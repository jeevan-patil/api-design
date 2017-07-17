package xyz.jeevan.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.jeevan.api.domain.message.ResponseMessage;
import xyz.jeevan.api.utils.APIEndpoints;

/**
 * Created by jeevan on 16/7/17.
 */
@RestController
@Api(value = "projects")
@RequestMapping(value = APIEndpoints.PROJECT_API_URL)
public class ProjectController extends BaseController {

  @ApiOperation(value = "Fetch project by id.",
      notes = "API to retrieve a single organization.", response = ResponseMessage.class)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseMessage> delete(@PathVariable final String id) {
    return new ResponseEntity<>(success("You asked for project with id: " + id),
        HttpStatus.OK);
  }
}
