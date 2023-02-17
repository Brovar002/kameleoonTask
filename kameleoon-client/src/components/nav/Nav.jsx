import { Link } from "react-router-dom";

import './Nav.css';
import KameleoonLogo from '../../assets/img/kameleoon.logo.png'

import { LOCALIZATION } from "../../constants/localization";

export const Nav = () => {
    return(
        <nav className="main-nav">
            <ul className="main-nav__ul">
                <li className="main-nav__ul_li">
                    <Link to='/profile'>
                        <img
                            src={KameleoonLogo}
                            alt="kameleoon-logo"
                            className="kameleoon-logo-img"
                        />
                    </Link>
                </li>
                <li className="main-nav__ul_li">
                    <Link to='/profile'>{LOCALIZATION.nav.profile}</Link>
                </li>
                <li className="main-nav__ul_li">
                    <Link to='/top10'>{LOCALIZATION.nav.top10}</Link>
                </li>
                <li className="main-nav__ul_li">
                    <Link to='/flop10'>{LOCALIZATION.nav.flop10}</Link>
                </li>
                <li className="main-nav__ul_li">
                    <Link to='/last'>{LOCALIZATION.nav.last}</Link>
                </li>
            </ul>
        </nav>
    );
}