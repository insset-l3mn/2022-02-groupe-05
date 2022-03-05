import React, {useEffect, useState} from "react";
import Form from "../../Components/Form/Form";
import InputFloating from "../../Components/InputFloating/InputFloating";
import InputSelect from "../../Components/InputSelect/InputSelect";
import axios from "axios";
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";

export default function AddQuestion(props){

    const [domainList, setDomainList] = useState([]);
    const [skillList, setSkillList] = useState([]);

    const [error, setError] = useState(false);
    const [success, setSuccess] = useState(false);

    const [question, setQuestion] = useState({
        contents:"",
        level:"",
        difficulty:"",
        domainName:"",
        skillName:"",
        right_answer:"",
        wrong_answer_1:"",
        wrong_answer_2:"",
        wrong_answer_3:""
    });

    useEffect(() => {
        axios.get("http://localhost:8080/gestion-formation-BE/api/domain/read/99/0")
            .then((response) => {
                setDomainList(response["data"]);
            });
        axios.get("http://localhost:8080/gestion-formation-BE/api/skill/read/99/0")
            .then((response) => {
                setSkillList(response["data"]);
            });
    },[])

    useEffect(() => {
        setSuccess(false)
        setError(false)
    },[props.type])

    const handleSubmit = e => {
        e.preventDefault();
        console.log(question)
        const url = "http://localhost:8080/gestion-formation-BE/api/question/add"

        const params = new URLSearchParams()
        params.append('level', question.level)
        params.append('difficulty', question.difficulty)
        params.append('contents', question.contents)
        params.append('domainName', question.domainName)
        params.append('skillName', question.skillName)
        params.append('right_answer', question.right_answer)
        params.append('wrong_answer_1', question.wrong_answer_1)
        params.append('wrong_answer_2', question.wrong_answer_2)
        params.append('wrong_answer_3', question.wrong_answer_3)

        const config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }

        axios.post(url, params, config)
            .then((response) => {
                console.log(response)
                response["data"]["type"] === "error" ? setError(response["data"]["message"]) : setSuccess(response["data"]["message"])

            })
            .catch((error) => {
                console.log(error)
            })

    }

    const onChangeInput = e => {
        const {name, value} = e.target;
        setQuestion(prevState => ({
            ...prevState,
            [name]: value
        }));
    }
    
    return (
        <>
            <div className="d-flex h-100 text-center text-white bg-dark align-items-center">
                <div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
			        <h1>Ajouter une question</h1>
                    <br/>

                    {error && <Error message={error}/>}
                    {success && <Success message={success}/>}

                    <Form labelButton={"Ajouter"} onSubmit={handleSubmit}>

                        <InputFloating id="floatingInputQuestionContenu"
                                       type="text"
                                       name={"contents"}
                                       labelContent={"Contenu"}
                                       placeholder={"Contenu"}
                                       onChange={onChangeInput}/>

                        <InputFloating id="floatingInputQuestionNiveau"
                                       type="number"
                                       name={"level"}
                                       labelContent={"Niveau"}
                                       placeholder={"Niveau"}
                                       onChange={onChangeInput}/>

                        <InputFloating id="floatingInputQuestionDifficulte"
                                       type="text"
                                       name={"difficulty"}
                                       labelContent={"Difficulté"}
                                       placeholder={"Difficulté"}
                                       onChange={onChangeInput}/>

                        <InputSelect id="floatingInputQuestionDomaine"
                                     type="select"
                                     name={"domainName"}
                                     labelContent={"Domaine"}
                                     placeholder={"Domaine"}
                                     onChange={e => setQuestion(prevState => ({
                                         ...prevState,
                                         domainName: e.target.value
                                     }))}>
                            {domainList.map((item) => <option value={item.denominate}>{item.denominate}</option>)}
                        </InputSelect>

                        <InputSelect id="floatingInputQuestionSkill"
                                     type="select"
                                     name={"skillName"}
                                     labelContent={"Compétence"}
                                     placeholder={"Compétence"}
                                     onChange={e => setQuestion(prevState => ({
                                         ...prevState,
                                         skillName: e.target.value
                                     }))}>
                            {skillList.map((item) => <option value={item.name}>{item.name}</option>)}
                        </InputSelect>
                        <div className="row">
                            <div className="col">
                                <InputFloating id="floatingInputReponse1"
                                               type="text"
                                               name={"right_answer"}
                                               labelContent={"Bonne réponse"}
                                               placeholder={"Bonne réponse"}
                                               onChange={onChangeInput}/>
                                <InputFloating id="floatingInputReponse2"
                                               type="text"
                                               name={"wrong_answer_1"}
                                               labelContent={"Mauvaise réponse"}
                                               placeholder={"Mauvaise réponse"}
                                               onChange={onChangeInput}/>
                            </div>
                            <div className="col">
                                <InputFloating id="floatingInputReponse3"
                                               type="text"
                                               name={"wrong_answer_2"}
                                               labelContent={"Mauvaise réponse"}
                                               placeholder={"Mauvaise réponse"}
                                               onChange={onChangeInput}/>
                                <InputFloating id="floatingInputReponse4"
                                               type="text"
                                               name={"wrong_answer_3"}
                                               labelContent={"Mauvaise réponse"}
                                               placeholder={"Mauvaise réponse"}
                                               onChange={onChangeInput}/>
                            </div>
                        </div>

                    </Form>
                </div>
            </div>
        </>
    );

}