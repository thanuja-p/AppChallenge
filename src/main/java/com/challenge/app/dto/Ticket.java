package com.challenge.app.dto;

import java.util.*;

/**
 * Ticket - This class is used to hold Ticket Json
 */
public class Ticket implements Searchable, Printable {

    public static final String ID_KEY = "_id";
    public static final String URL_KEY = "url";
    public static final String EXTERNAL_ID = "external_id";
    public static final String CREATED_AT = "created_at";
    public static final String TYPE_KEY = "type";
    public static final String SUBJECT_KEY = "subject";
    public static final String DESCRIPTION_KEY = "description";
    public static final String PRIORITY_KEY = "priority";
    public static final String STATUS_KEY = "status";
    public static final String SUBMITTER_ID = "submitter_id";
    public static final String ASSIGNEE_ID = "assignee_id";
    public static final String ORGANIZATION_ID = "organization_id";
    public static final String TAGS_KEY = "tags";
    public static final String HAS_INCIDENTS = "has_incidents";
    public static final String DUE_AT = "due_at";
    public static final String VIA_KAY = "via";

    private String id;
    private String url;
    private String externalId;
    private String createdAt;
    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private int submitterId;
    private int assigneeId;
    private int organizationId;
    private List<String> tags;
    private boolean hasIncidents;
    private String dueAt;
    private String via;

    public Ticket() {
    }

    public Ticket(Map<String, Object> userMap) {
        this.id = (String) userMap.get(ID_KEY);
        this.url = (String) userMap.getOrDefault(URL_KEY, "");
        this.externalId = (String) userMap.getOrDefault(EXTERNAL_ID, "");
        this.createdAt = (String) userMap.getOrDefault(CREATED_AT, "");
        this.type = (String) userMap.getOrDefault(TYPE_KEY, "");
        this.subject = (String) userMap.getOrDefault(SUBJECT_KEY, "");
        this.description = (String) userMap.getOrDefault(DESCRIPTION_KEY, "");
        this.priority = (String) userMap.getOrDefault(PRIORITY_KEY, "");
        this.status = (String) userMap.getOrDefault(STATUS_KEY, "");
        this.submitterId = (int) userMap.getOrDefault(SUBMITTER_ID, 0);
        this.assigneeId = (int) userMap.getOrDefault(ASSIGNEE_ID, 0);
        this.organizationId = (int) userMap.getOrDefault(ORGANIZATION_ID, 0);
        this.tags = (List<String>) userMap.getOrDefault(TAGS_KEY, new ArrayList<>());
        this.hasIncidents = (boolean) userMap.getOrDefault(HAS_INCIDENTS, false);
        this.dueAt = (String) userMap.getOrDefault(DUE_AT, "");
        this.via = (String) userMap.getOrDefault(VIA_KAY, "");
    }

    /**
     * This method returns list of searchable fields for ticket.
     *
     * @return list of searchable fields
     */
    @Override
    public List<String> getSearchableFields() {
        return Arrays.asList(ID_KEY, URL_KEY, EXTERNAL_ID, CREATED_AT,
                TYPE_KEY, SUBJECT_KEY, DESCRIPTION_KEY, PRIORITY_KEY, STATUS_KEY,
                SUBMITTER_ID, ASSIGNEE_ID, ORGANIZATION_ID, TAGS_KEY,
                HAS_INCIDENTS, DUE_AT, VIA_KAY);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(int submitterId) {
        this.submitterId = submitterId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isHasIncidents() {
        return hasIncidents;
    }

    public void setHasIncidents(boolean hasIncidents) {
        this.hasIncidents = hasIncidents;
    }

    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    /**
     * This method returns map for ticket.
     *
     * @return map for ticket
     */
    public Map<String, String> getPrintMap() {
        Map<String, String> printMap = new LinkedHashMap<>();
        printMap.put(ID_KEY, id);
        printMap.put(URL_KEY, url);
        printMap.put(EXTERNAL_ID, externalId);
        printMap.put(CREATED_AT, createdAt);
        printMap.put(TYPE_KEY, type);
        printMap.put(SUBJECT_KEY, subject);
        printMap.put(DESCRIPTION_KEY, description);
        printMap.put(PRIORITY_KEY, priority);
        printMap.put(STATUS_KEY, status);
        printMap.put(SUBMITTER_ID, String.valueOf(submitterId));
        printMap.put(ASSIGNEE_ID, String.valueOf(assigneeId));
        printMap.put(ORGANIZATION_ID, String.valueOf(organizationId));
        printMap.put(TAGS_KEY, String.valueOf(tags));
        printMap.put(HAS_INCIDENTS, String.valueOf(hasIncidents));
        printMap.put(DUE_AT, dueAt);
        printMap.put(VIA_KAY, via);
        return printMap;
    }
}
