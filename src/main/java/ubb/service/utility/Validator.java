package ubb.service.utility;

import ubb.utils.ApplicationException;

public interface Validator<T> {

    /**
     * Validates an object of type T
     * @param t - T type
     * @throws ApplicationException - if t doesn't pass the validation criteria
     */
    void validation(T t) throws ApplicationException;
}
