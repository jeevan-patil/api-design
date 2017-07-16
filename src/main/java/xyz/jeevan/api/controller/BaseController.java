package xyz.jeevan.api.controller;

import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;
import java.util.List;
import springfox.documentation.annotations.ApiIgnore;
import xyz.jeevan.api.domain.message.ResponseMessage;
import xyz.jeevan.api.utils.AppConstants;
import xyz.jeevan.api.utils.AttributeProvider;
import xyz.jeevan.api.utils.FieldConstants;

/**
 * Created by jeevan on 11/6/17.
 */
@ApiIgnore
public class BaseController {

  private JsonResult json = JsonResult.instance();

  private <T> T adjustFields(T data, Class<T> clazz, String[] excludeFields,
      String[] includeFields) {
    return json.use(JsonView.with(data)
        .onClass(clazz, Match.match()
            .exclude(excludeFields)
            .include(includeFields)))
        .returnValue();
  }

  private <T> List<T> adjustFields(List<T> data, Class<T> clazz,
      String[] excludeFields,
      String[] includeFields) {
    return json.use(JsonView.with(data)
        .onClass(clazz, Match.match()
            .exclude(excludeFields)
            .include(includeFields)))
        .returnValue();
  }

  protected <T> List<T> limitDataFields(List<T> data, Class<T> clazz, String fields) {
    return adjustFields(data, clazz, FieldConstants.EXCLUDEALL_FIELDS,
        AttributeProvider.provideFields(clazz, fields));
  }

  protected <T> List<T> limitDataFields(List<T> data, Class<T> clazz) {
    return adjustFields(data, clazz, FieldConstants.EXCLUDEALL_FIELDS,
        AttributeProvider.provideFields(clazz, ""));
  }

  protected <T> T limitDataFields(T data, Class<T> clazz, String fields) {
    return adjustFields(data, clazz, FieldConstants.EXCLUDEALL_FIELDS,
        AttributeProvider.provideFields(clazz, fields));
  }

  protected <T> T limitDataFields(T data, Class<T> clazz) {
    return adjustFields(data, clazz, FieldConstants.EXCLUDEALL_FIELDS,
        AttributeProvider.provideFields(clazz, ""));
  }

  protected ResponseMessage success(String message) {
    return new ResponseMessage(AppConstants.SUCCESS, message);
  }

}
