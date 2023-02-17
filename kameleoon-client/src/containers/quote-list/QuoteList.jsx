import PropTypes from "prop-types";

import { UserBar } from "../../components/user-bar/UserBar";
import { Quote } from "../../components/quote/Quote";

const mockQuotes = [
    {
        id: 1,
        content: 'Note that the development build is not optimized.',
        createdAt: new Date(),
        userId: 1,
        votes: 5,
    },
    {
        id: 2,
        content: 'Note that the development build is not optimized.',
        createdAt: new Date(),
        userId: 1,
        votes: 5,
    },
    {
        id: 3,
        content: 'Note that the development build is not optimized.',
        createdAt: new Date(),
        userId: 2,
        votes: 5,
    },
    {
        id: 4,
        content: 'Note that the development build is not optimized.',
        createdAt: new Date(),
        userId: 4,
        votes: 5,
    },
    {
        id: 5,
        content: 'Note that the development build is not optimized.',
        createdAt: new Date(),
        userId: 3,
        votes: 5,
    },
];

export const QuoteList = (props) => {
    return(
        <div className="quote-list-container">
            <div className="quote-list">
                {
                    mockQuotes.map(
                        (quote) => (
                            <Quote
                                id={quote.id}
                                content={quote.content}
                                createdAt={quote.createdAt}
                                userId={quote.userId}
                                votes={quote.votes}
                            />
                        )
                    )
                }
            </div>
            <UserBar />
        </div>
    );
}

QuoteList.propTypes = {
    type: PropTypes.string,
}
