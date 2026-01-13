import {Container, Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router";

export function AppNav() {
    return (
        <Navbar expand="lg" className="bg-body-tertiary" bg="dark" data-bs-theme="dark">
            <Container>
                <Navbar.Brand href="/">WEDELA</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Link to={"/"} className={"nav-link"}>Home</Link>
                        <Link to={"/about"} className={"nav-link"}>About</Link>
                        <Link to={"/contact"} className={"nav-link"}>Contact</Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}