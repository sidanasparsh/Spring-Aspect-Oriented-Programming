# Spring-Aspect-Oriented-Programming
In this lab, you implement the retry and authorization concerns for a profile service through Aspect Oriented Programming (AOP).  The profile service allows access to and sharing of profiles. One can share his profile with others, and can share  with others the profiles he is shared with too. 

The profile service is defined as follows:

package edu.sjsu.cmpe275.aop;

public interface ProfileService {
	/**
 	* Read the profile of another user or oneself.
 	* @param userId the ID of the current user
 	* @param profileUserId the ID of user, whose profile is being requested
 	* @return the profile for profileUserId  
 	*/
	Profile readProfile(String userId, String profileUserId)  throws AccessDeniedExeption, NetworkException;
    
	/**
 	* Share a profile with another user. The profile may or may not belong to the current user.
 	* @param userId the ID of the current user
 	* @param profileUserId the ID of the user, whose profile is being shared
 	* @param targetUserId the ID of the user to share the profile with
 	*/
	void shareProfile(String userId, String profileUserId, String targetUserId)  throws AccessDeniedExeption, NetworkException;
    
	/**
 	* Unshare the current user's own profile with another user.
 	* @param userId
 	* @param targetUserId
 	*/
	void unshareProfile(String userId, String targetUserId)  throws AccessDeniedExeption, NetworkException;
}

The actual implementation of the service, the “official” version of ProfileServiceImpl.java, however, does not provide enforce access control; i.e., the shareProfile method does not check whether the current user is shared with the profile in the first place, and readProfile does not to check that either. Part of your task is to use AOP to enforce the following authorization policies. 

1.	Once can share his profile with anybody.
2.	One can only read profiles that are shared with him, or his own profile. In any other case, an AccessDeniedExeption is thrown.
3.	If Alice shares her profile with Bob, Bob can further share Alice’s profile with Carl. If Alice attempts to share Bob’s profile with Carl while Bob’s profile is not shared with Alice in the first place, Alice gets an AccessDeniedExeption.
4.	One can only unshare his own profile. When unsharing a profile with Bob that the profile is not shared by any means with Bob in the first place, the operation throws an AccessDeniedExeption. 
5.	Both sharing and unsharing of Alice’s profile with Alice have no effect; i.e. Alice always has access to her own profile, and can share and unshare with herself without encountering any exception, even these operations do not take any effect.

Please note that our access control here assumes that authentication is already taken care of elsewhere, i.e., it’s outside the scope of the project to make sure only Alice can call readProfile with userId as “Alice”.

All the methods in ProfileService can also run into network failures, in which case, an NetworkException will be thrown, and the method takes no effect at all. Actually, since network failure happens relatively frequently, you are asked to add the feature to automatically retry for up to two times for a network failure (indicated by an NetworkException). Please note the two retries are in addition to the original failed invocation; if you still encounter NetworkException on your second retry, you should throw out this NetworkException so that the caller knows its occurrence.


Your implementation of the two concerns need to be done in the two files: RetryAspect.java and AuthorizationAspect.java. For example, RetryAspect.java should look like the following:

package edu.sjsu.cmpe275.aop.aspect;
import org.aspectj.lang.annotation.Aspect;  // if needed
import org.aspectj.lang.annotation.Before;  // if needed

@Aspect
public class RetryAspect {
     ...
}

You do not need to worry about multi-threading; i.e., you can assume invocations on the sharing service will come from only one thread.

For your testing purpose, you need to provide your own implementation of ProfileServiceImpl.java, and simulate failures, but you do not need to submit this file, as the TA will use his own implementation(s) for grading purpose.
