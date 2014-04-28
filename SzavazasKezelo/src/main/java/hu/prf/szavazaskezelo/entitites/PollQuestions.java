/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.entitites;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ano4Ever
 */
@Entity
@Table(name = "poll_questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PollQuestions.findAll", query = "SELECT p FROM PollQuestions p"),
    @NamedQuery(name = "PollQuestions.findById", query = "SELECT p FROM PollQuestions p WHERE p.id = :id"),
    @NamedQuery(name = "PollQuestions.findByPollQuestion", query = "SELECT p FROM PollQuestions p WHERE p.pollQuestion = :pollQuestion"),
    @NamedQuery(name = "PollQuestions.findByMultiple", query = "SELECT p FROM PollQuestions p WHERE p.multiple = :multiple")})
public class PollQuestions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "poll_question")
    private String pollQuestion;
    @Column(name = "multiple")
    private Boolean multiple;
    @OneToMany(mappedBy = "pollQuestionId", fetch = FetchType.EAGER)
    private Set<PollFillings> pollFillingsSet;
    @JoinColumn(name = "poll_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Polls pollId;
    @OneToMany(mappedBy = "pollQuestionId", fetch = FetchType.EAGER)
    private Set<PollAnswers> pollAnswersSet;

    public PollQuestions() {
    }

    public PollQuestions(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    @XmlTransient
    public Set<PollFillings> getPollFillingsSet() {
        return pollFillingsSet;
    }

    public void setPollFillingsSet(Set<PollFillings> pollFillingsSet) {
        this.pollFillingsSet = pollFillingsSet;
    }

    public Polls getPollId() {
        return pollId;
    }

    public void setPollId(Polls pollId) {
        this.pollId = pollId;
    }

    @XmlTransient
    public Set<PollAnswers> getPollAnswersSet() {
        return pollAnswersSet;
    }

    public void setPollAnswersSet(Set<PollAnswers> pollAnswersSet) {
        this.pollAnswersSet = pollAnswersSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PollQuestions)) {
            return false;
        }
        PollQuestions other = (PollQuestions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ((pollId != null) ? pollId.toString() : "Nincs Szavazás") + pollQuestion ;
    }
    
}
