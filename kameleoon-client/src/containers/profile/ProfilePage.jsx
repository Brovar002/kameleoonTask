import { Profile } from '../../components/profile/Profile';
import './ProfilePage.css';

export const ProfilePage = () => {
    const name = 'Daniil';
    const email = 'brovar002@gmail.com';

    return(
        <div className="profile-container">
            <Profile name={name} email={email} />
        </div>
    );
}