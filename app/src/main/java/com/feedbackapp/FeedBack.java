package com.feedbackapp;

/**
 * Created by pshar10 on 11/22/2015.
 */
public class FeedBack {

    //private variables
    int _id;
    String _fname;
    String _lname;
    String _vote;
    String _review;
    int _review_type;

    // Empty constructor
    public FeedBack(){

    }

    // constructor
    public FeedBack(int id, String fname, String lname, String vote, String review, int review_type){
        this._id = id;
        this._fname = fname;
        this._lname = lname;
        this._vote = vote;
        this._review = review;
        this._review_type = review_type;
    }

    // constructor
    public FeedBack(String vote, String review, int review_type){
        this._vote = vote;
        this._review = review;
        this._review_type = review_type;
    }

    public String get_fname() {
        return _fname;
    }

    public void set_fname(String _fname) {
        this._fname = _fname;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_vote() {
        return _vote;
    }

    public void set_vote(String _vote) {
        this._vote = _vote;
    }

    public String get_lname() {
        return _lname;
    }

    public void set_lname(String _lname) {
        this._lname = _lname;
    }

    public String get_review() {
        return _review;
    }

    public void set_review(String _review) {
        this._review = _review;
    }

    public int get_review_type() {
        return _review_type;
    }

    public void set_review_type(int _review_type) {
        this._review_type = _review_type;
    }
}
