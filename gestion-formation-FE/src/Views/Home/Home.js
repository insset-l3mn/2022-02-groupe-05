import React from "react";
import './Home.css';
import Particles from "react-tsparticles";
import Container from "../../Components/Container/Container";

export default function Home(){

    const home = () => {
        const particlesInit = (main) => {
        };
        const particlesLoaded = (container) => {
        };

        return (
            <Particles
                id="particles-js"
                init={particlesInit}
                loaded={particlesLoaded}
                options={{

                    fpsLimit: 120,
                    interactivity: {
                        events: {

                            resize: true,
                        },
                        modes: {
                            bubble: {
                                distance: 400,
                                duration: 2,
                                opacity: 0.8,
                                size: 40,
                            },
                            push: {
                                quantity: 4,
                            },
                            repulse: {
                                distance: 200,
                                duration: 0.4,
                            },
                        },
                    },
                    particles: {
                        color: {
                            value: "#ffffff",
                        },
                        links: {
                            color: "#ffffff",
                            distance: 150,
                            enable: true,
                            opacity: 0.5,
                            width: 1,
                        },
                        collisions: {
                            enable: true,
                        },
                        move: {
                            direction: "none",
                            enable: true,
                            outMode: "bounce",
                            random: false,
                            speed: 2,
                            straight: false,
                        },
                        number: {
                            density: {
                                enable: true,
                                area: 800,
                            },
                            value: 70,
                        },
                        opacity: {
                            value: 0.5,
                        },
                        shape: {
                            type: "circle",
                        },
                        size: {
                            random: true,
                            value: 5,
                        },
                    },
                    detectRetina: true,
                }}
            />
        );
    }

    return (
        <>
            <Container>
                <main className="px-3">
                    <h1>Evaluez vos compétences !</h1>
                    <p className="lead">
                        Avec Skill-Reader, montrez vos compétences afin de nous permettre de vous proposer des parcours
                        de formation personnalisés générés pour vous.
                    </p>
                    <p className="lead">
                        <a style={{color:"#333"}} className="btn btn-lg btn-secondary fw-bold border-white bg-white">C'est parti !</a>
                    </p>
                </main>
                {/* Particules */}
                {home()}

            </Container>
        </>

    );

}