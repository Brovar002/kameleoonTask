import PropTypes from 'prop-types';

import './Profile.css';

import { LOCALIZATION } from '../../constants/localization';

// props:
// name,
// 
export const Profile = (props) => {
    return(
        <div className="profile">
            <h3>{LOCALIZATION.nav.profile}</h3>
            <p>
                {LOCALIZATION.profile.login}: <b>{props?.name || '-'}</b>
            </p>
            <p>
                {LOCALIZATION.profile.email}: <b>{props?.email || '-'}</b>
            </p>
        </div>
    );
}

Profile.propTypes = {
    name: PropTypes.string,
    email: PropTypes.string,
}