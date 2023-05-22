package isuue;

import java.util.List;

public class CommandChangeList {

    static public List<String> getAddressListString(List<String> children) {
        for (int i = 0; i < children.size(); i++) {
            String[] temp = children.get(i).split("-");
            children.remove(i);
            children.add(i, temp[0]);
        }
        return children;
    }
}
