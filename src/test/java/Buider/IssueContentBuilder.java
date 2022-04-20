package Buider;

import module.IssueFields;

public class IssueContentBuilder {
    // Build hàm để tạo 1 Json từ java object
    // get ra Java object


    public IssueFields getIssueFields() {
        return issueFields;
    }

    private IssueFields issueFields;

    public String build(String summary, String projectKey, String issuetypeID) {

        IssueFields.Issuetype issuetype = new IssueFields.Issuetype(issuetypeID);
        IssueFields.Project project = new IssueFields.Project(projectKey);

        IssueFields.Fields fields = new IssueFields.Fields(summary, project, issuetype);
        issueFields = new IssueFields(fields);
        return BodyJSONBuilder.getJSONContent(issueFields);


    }
}
