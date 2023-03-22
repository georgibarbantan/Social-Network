package Domain.validators;

import Domain.User;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        validateName(entity.getFirstName(), entity.getLastName());
        validateUserName(entity.getUserName());
        validatePassword(entity.getPasword());
    }
    public void validateName(String firstN,String lastN){
        if(firstN==null)
            throw new ValidationException("First name musn't be null!");
        else if(lastN==null)
            throw new ValidationException("Last name musn't be null!");
        else if(lastN.isEmpty())
            throw new ValidationException("Last name musn't be empty!");
        else if(firstN.isEmpty())
            throw new ValidationException("First name musn't be empty!");
        else if(firstN.length()>50)
            throw new ValidationException("First name too long!");
        else if(lastN.length()>50)
            throw new ValidationException("Last name too long!");
    }
    public void validateUserName(String username){
        if(username==null)
            throw new ValidationException("User name musn't be null!");
        else if(username.isEmpty())
            throw new ValidationException("User name musn't be empty!");
        else if(username.length()>50)
            throw new ValidationException("User name too long!");
    }
    public void validatePassword(String password){
        if(password==null)
            throw new ValidationException("Password name musn't be null!");
        else if(password.isEmpty())
            throw new ValidationException("Password name musn't be empty!");
        else if(password.length()>50)
            throw new ValidationException("Password name too long!");
    }
}
