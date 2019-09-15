package com.challenge.app.searchers;

import com.challenge.app.dto.SearchType;
import com.challenge.app.exceptions.SearchException;
import com.challenge.app.searchers.jsons.OrganizationSearcher;
import com.challenge.app.searchers.jsons.TicketSearcher;
import com.challenge.app.searchers.jsons.UserSearcher;
import com.challenge.app.searchers.mediators.OrganizationSearchMediator;
import com.challenge.app.searchers.mediators.SearchMediator;
import com.challenge.app.searchers.mediators.TicketSearchMediator;
import com.challenge.app.searchers.mediators.UserSearchMediator;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

public class SearchFactory {

    private UserSearcher userSearcher;
    private TicketSearcher ticketSearcher;
    private OrganizationSearcher organizationSearcher;

    public SearchFactory(String userJsonFilePath,
                         String ticketJsonFilePath,
                         String organizationFilePath) {
        try {
            this.userSearcher = new UserSearcher(getJsonContext(userJsonFilePath));
            this.ticketSearcher = new TicketSearcher(getJsonContext(ticketJsonFilePath));
            this.organizationSearcher = new OrganizationSearcher(getJsonContext(organizationFilePath));
        } catch (IOException e) {
            throw new SearchException("Invalid file path", e);
        }
    }

    /**
     * This method returns DocumentContext for Json file.
     *
     * @param filePath filePath
     * @return DocumentContext for Json file
     * @throws IOException
     */
    private DocumentContext getJsonContext(String filePath) throws IOException {
        File jsonFile = new File(filePath);
        return JsonPath.parse(jsonFile);
    }

    /**
     * This method returns SearchMediator relevant to searchType.
     *
     * @param searchType searchType
     * @return SearchMediator object
     */
    public SearchMediator getSearcher(SearchType searchType) {
        switch (searchType) {
            case USER:
                return new UserSearchMediator(userSearcher, ticketSearcher, organizationSearcher);
            case TICKET:
                return new TicketSearchMediator(userSearcher, ticketSearcher, organizationSearcher);
            case ORHANIZATION:
                return new OrganizationSearchMediator(userSearcher, ticketSearcher, organizationSearcher);
            default:
                throw new SearchException("Unsupported search type");
        }

    }
}
