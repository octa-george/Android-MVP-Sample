package ro.octa.sample.commons.model;

import java.io.Serializable;

/**
 * @author Octa
 */
public interface HasId<ID extends Serializable> {

    ID getId();
}
