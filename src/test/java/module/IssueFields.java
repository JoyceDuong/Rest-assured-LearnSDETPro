package module;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class IssueFields {
    // Cách tạo nested object

    private Fields fields;

    public IssueFields(Fields fields) {
        this.fields = fields;
    }

    @Data
    public static class Fields {
        private String summary;
        private Project project;

        public Fields(String summary, Project project, Issuetype issuetype) {
            this.summary = summary;
            this.project = project;
            this.issuetype = issuetype;
        }

        private Issuetype issuetype;

    }

    @Data
    public static class Project {
        private String key;

        public Project(String key) {
            this.key = key;
        }
    }

    @Data
    public static class Issuetype {
        private String id;

        public Issuetype(String id) {
            this.id = id;
        }
    }


}
