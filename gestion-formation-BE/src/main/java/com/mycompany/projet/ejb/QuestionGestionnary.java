/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfQuestion;
import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.User;
import com.mycompany.projet.entities.UserHasSkill;
import java.util.Collection;
import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author valen
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/gestion-formation",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "root",
        databaseName = "gestion-formation",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class QuestionGestionnary {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private UserGestionnary userGestionnary;
    
    @EJB
    private UserHasSkillGestionnary userHasSkillGestionnary;

    public void createQuestion(GfQuestion question) {
        em.persist(question);
    }

    public Boolean existQuestion(String contents) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.contents=:contents")
                .setParameter("contents", contents);

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }

    public Boolean existQuestion(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.idQuestion=:id")
                .setParameter("id", id);

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }

    public Boolean updateQuestion(int id, Integer difficulty, String contents, String skillName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        GfSkill skill;
        try {
            skill = SkillGestionnary.requestSkill(skillName);
        } catch (Exception e) {
            return false;
        }

        try {
            em1.createQuery("UPDATE GfQuestion q SET q.difficulty=:difficulty, q.contents=:contents, q.idSkill=:idSkill WHERE q.idQuestion=:id")
                    .setParameter("difficulty", difficulty)
                    .setParameter("contents", contents)
                    .setParameter("idSkill", skill)
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeQuestion(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        try {
            em1.createQuery("DELETE FROM GfQuestion WHERE idQuestion=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public GfQuestion readQuestion(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.idQuestion=:id")
                .setParameter("id", id);

        GfQuestion question = null;

        if (!query.getResultList().isEmpty()) {
            question = (GfQuestion) query.getResultList().get(0);
        }

        return question;
    }

    public GfQuestion readQuestion(String str) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.contents=:str")
                .setParameter("str", str);

        GfQuestion question = null;

        if (!query.getResultList().isEmpty()) {
            question = (GfQuestion) query.getResultList().get(0);
        }

        return question;
    }

    public List readQuestions(int count, int startAt) {
        return em.createQuery(
                "SELECT q FROM GfQuestion q")
                .setFirstResult(startAt)
                .setMaxResults(count)
                .getResultList();
    }

    public boolean isOwnerOfQuestion(int idQuestion, int userID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE (q.idTrainer.id_user=:idUser AND q.idQuestion=:idQuestion)")
                .setParameter("idQuestion", idQuestion)
                .setParameter("idUser", userID);

        if (!query.getResultList().isEmpty()) {
            return false;
        }

        return true;
    }

    public int countQuestions() {
        return em.createQuery("SELECT q FROM GfQuestion q").getResultList().size();
    }

    public void updateQuestionUserCollection(int questionId, int userId) {
        GfQuestion q = em.find(GfQuestion.class, questionId);
        Collection c = q.getUserCollection();
        c.add(userGestionnary.readUser(userId));
        em.persist(q);
    }
    
    public boolean existQuestionUserCollection(int questionId, int userId) {
        GfQuestion q = readQuestion(questionId);
        User u = userGestionnary.readUser(userId);
        return q.getUserCollection().contains(u);
        
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q JOIN q.userCollection u WHERE u.id_user=:userId")
                .setParameter("id", questionId)
                .setParameter("userId", userId);
        
        if (!query.getResultList().isEmpty()) {
            return false;
        }
        return true;*/
    }
    
    public GfQuestion getQuestionQuestionnary(int userId, int difficulty){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM UserHasSkill q WHERE q.user.id_user=:idUser AND q.successiveError < 4 AND q.malus < 10")
                .setParameter("idUser", userId);
        if(query.getResultList().isEmpty()) return null;//QCM TERMINE
        
        int max = query.getResultList().size()-1;
        int random = (int)(Math.random() * ( max ));
        UserHasSkill s = (UserHasSkill)query.getResultList().get(random);
        
        
        query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.difficulty=:difficulty AND q.idSkill.idSkill=:skillId")
                .setParameter("skillId", s.getGfSkill().getIdSkill())
                .setParameter("difficulty", Math.round(difficulty));
        
        List<GfQuestion> questions = query.getResultList();
        if(questions.isEmpty())userHasSkillGestionnary.increaseMalus(userId, s.getGfSkill().getIdSkill(), 999);
       
        //WHERE q.IDuser != userId
        
        query = em1.createQuery("SELECT q FROM GfQuestion q JOIN q.userCollection u WHERE u.id_user=:userId")
                .setParameter("userId", userId);
        
        List<GfQuestion> alreadyUsedQuestions = query.getResultList();
        if(alreadyUsedQuestions.isEmpty()) return questions.get(0);
            
        for(int i = 0; i < questions.size(); i++){
            GfQuestion q = questions.get(i);
            
            query = em1.createQuery("SELECT q FROM GfQuestion q JOIN q.userCollection u WHERE u.id_user=:userId and q.idQuestion=:questionId")
                .setParameter("userId", userId)
                .setParameter("questionId", q.getIdQuestion());
            
            if(query.getResultList().isEmpty()) return q;
            
        }
        if(difficulty == 3){
            userHasSkillGestionnary.increaseMalus(userId, s.getGfSkill().getIdSkill(), 999);
            return null;
        }
        return getQuestionQuestionnary(userId, difficulty+1);
    }
}
