import React, {forwardRef, useImperativeHandle, useState} from "react";
import {Modal} from "react-bootstrap";

const ModalView = forwardRef((props, ref) => {

    const [show, setShow] = useState(true);

    const handleClose = () => {
        setShow(false)
    }
    const handleShow = () => setShow(true);

    useImperativeHandle(ref, () => ({
        handleClose
    }));

    return (
        <>

            <Modal {...props} show={show} onHide={handleClose}>
                {props.children}
            </Modal>
        </>
    );

});

export default ModalView
