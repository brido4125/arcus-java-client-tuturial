package functional.optional;

import java.util.Optional;

public class OnlineClass {
    private Integer id;
    private String title;

    private boolean closed;

    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isClosed() {
        return closed;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    //Optional로 감싸고 리턴 -> nullable한 값을 처리 가능
    public Optional<Progress> getProgress() {
        return Optional.empty();
    }

    //인자 자체에 null 값이 들어올 가능성 존재
    public void setProgress(Optional<Progress> progress) {
        progress.ifPresent(p -> this.progress = p);
    }
}
