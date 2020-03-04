# SmsRetrieverClient-API
  SMS Retriever Client API for SMS in android device.
  
## Public Methods
public abstract Task<Void> startSmsRetriever ()
Starts SmsRetriever, which waits for a matching SMS message until timeout (5 minutes). The matching SMS message will be sent via a Broadcast Intent with action SMS_RETRIEVED_ACTION. The Intent contains Extras with keys EXTRA_SMS_MESSAGE for the retrieved SMS message as a String, and EXTRA_STATUS for Status to indicate SUCCESS, DEVELOPER_ERROR, ERROR, or TIMEOUT.

### Note: Add SEND_PERMISSION while registering the receiver to detect that the broadcast intent is from the SMS Retriever. Be aware that this detection only works on devices using Google Play services v19.8.31 and later.

### The possible causes for errors are:

DEVELOPER_ERROR: the caller app has incorrect number of certificates. Only one certificate is allowed.
ERROR: the AppCode collides with other installed apps.
Returns
a Task for the call. Attach an OnCompleteListener and then check isSuccessful() to determine if it was successful.
public abstract Task<Void> startSmsUserConsent (String senderAddress)
Starts SmsUserConsent, which waits for an OTP-containing SMS message until timeout (5 minutes). OTP-containing SMS message can be retrieved with two steps.

### Note: Add SEND_PERMISSION while registering the receiver to detect that the broadcast intent is from the SMS Retriever. Be aware that this detection only works on devices using Google Play services v19.8.31 and later.

[Get consent Intent] While OTP-containing SMS message comes, a consent Intent will be sent via a Broadcast Intent with action SMS_RETRIEVED_ACTION. The Intent contains Extras with keys EXTRA_CONSENT_INTENT for the consent Intent and EXTRA_STATUS for Status to indicate SUCCESS or TIMEOUT.
[Get OTP-containing SMS message] Calls startActivityForResult with consent Intent to launch a consent dialog to get user's approval, then the OTP-containing SMS message can be retrieved from the activity result.
Parameters
senderAddress	address of desired SMS sender, or null to retrieve any sender
Returns
a Task for the call. Attach an OnCompleteListener and then check isSuccessful() to determine if it was successful.
