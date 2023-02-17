import PropTypes from "prop-types";
import { useState } from "react";

import './Quote.css';

export const Quote = (props) => {
    const [isOpen, setIsOpen] = useState(false);

    const handleOpen = (e) => {
        e.preventDefault();

        setIsOpen(!isOpen);
    }

    return(
        <div className="quote">
            <h5>
                Quote { props.id }
            </h5>
            <button onClick={handleOpen}>
                Open
            </button>
            {
                isOpen && 
                <p>
                    { props.content }
                </p>
            }
        </div>
    );
}

Quote.propTypes = {
    id: PropTypes.number,
    content: PropTypes.string,
    createdAt: PropTypes.string,
    userId: PropTypes.number,
    votes: PropTypes.number,
}
