package ro.octa.sample.commons.model;

/**
 * @author Octa
 */
public class Album implements HasId<Long> {

    private long id;
    private long userId;
    private String title;

    @Override
    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}
