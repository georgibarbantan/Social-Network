package Domain.validators;

import Domain.Friendship;

public class FriendshipValidator implements Validator<Friendship>{
    @Override
    public void validate(Friendship entity) throws ValidationException {
        if(entity.getUser1().getId().equals(entity.getUser2().getId()))
            throw new ValidationException("We are sorry but this action is impossible.");
    }

}
