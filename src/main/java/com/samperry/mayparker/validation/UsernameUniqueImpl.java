package com.samperry.mayparker.validation;


import com.samperry.mayparker.database.dao.UserDAO;
import com.samperry.mayparker.database.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniqueImpl implements ConstraintValidator<UsernameUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(UsernameUniqueImpl.class);

    @Autowired
    private UserDAO userDao;

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        User user = userDao.findByUsername(value);

        return (user == null);
    }
}
