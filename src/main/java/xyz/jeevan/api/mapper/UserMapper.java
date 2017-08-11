package xyz.jeevan.api.mapper;

import com.mongodb.DBObject;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import xyz.jeevan.api.domain.User;

/**
 * @author jeevan
 */
public class UserMapper {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(UserMapper.class);

  public static User mapMongoObject(DBObject dbObject) {
    Field[] fields = User.class.getDeclaredFields();

    User user = new User();
    for (Field field : fields) {
      try {
        Object value;
        if ("roles".equalsIgnoreCase(field.getName())) {
          value = getRoles((List) dbObject.get(field.getName()));
        } else {
          value = dbObject.get(field.getName());
        }
        BeanUtils.setProperty(user, field.getName(), value);
      } catch (Exception e) {
        LOG.error("Error occurred while mapping authentication object to User object. {}",
            e.getMessage());
      }
    }
    return user;
  }

  private static Set<String> getRoles(List<String> authorities) {
    return new HashSet<>(authorities);
  }
}
