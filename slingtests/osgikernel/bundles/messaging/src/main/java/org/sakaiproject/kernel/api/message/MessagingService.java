/*
 * Licensed to the Sakai Foundation (SF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The SF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.sakaiproject.kernel.api.message;

import java.util.List;
import java.util.Map;

import javax.jcr.AccessDeniedException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.query.InvalidQueryException;

public interface MessagingService {
  
  /**
   * Creates a new message for the user associated with the provided session.
   * Message properties are extracted from the supplied map.
   * The messageId supplied must be guaranteed unique
   * 
   * @param resource
   * @param mapProperties
   * @param messageId Globally unique message identifier
   * @return
   * @throws MessagingException
   */
  public Node create(Session session, Map<String, Object> mapProperties, String messageId)
  throws MessagingException;
  
  /**
   * Creates a new message for the user associated with the provided session.
   * Message properties are extracted from the supplied map
   * @param resource
   * @param mapProperties
   * @return
   * @throws MessagingException
   */
  public Node create(Session session, Map<String, Object> mapProperties) throws MessagingException;

  /**
   * Gets the absolute path to the message store from a message. ex:
   * /_private/D0/33/E2/admin/messages
   * 
   * @param msg
   *          A message node
   * @return
   */
  public String getMessageStorePathFromMessageNode(Node msg)
      throws ValueFormatException, PathNotFoundException,
      ItemNotFoundException, AccessDeniedException, RepositoryException;

  /**
   * Gets the path for the message starting at the message store. ex:
   * /fd/e1/df/h1/45fsdf4sd453uy4ods4fa45r4
   * 
   * @param msg
   * @return
   * @throws ValueFormatException
   * @throws PathNotFoundException
   * @throws ItemNotFoundException
   * @throws AccessDeniedException
   * @throws RepositoryException
   */
  /*
  public String getMessagePathFromMessageStore(Node msg)
      throws ValueFormatException, PathNotFoundException,
      ItemNotFoundException, AccessDeniedException, RepositoryException;
      */

  /**
   * Searches for mailboxes on the system associated with a supplied e-mail address
   * @param session The session from which to execute the search
   * @param emailAddress The email address for which to search
   * @return A list of the mailbox / principal names
   */
  public List<String> getMailboxesForEmailAddress(Session session, String emailAddress) throws InvalidQueryException, RepositoryException;

  /**
   * Copies a message with id <em>messageId</em> from <em>source</em> to <em>target</em>
   * @param adminSession
   * @param target
   * @param source
   * @param messageId
   * @throws RepositoryException 
   * @throws PathNotFoundException 
   */
  public void copyMessage(Session adminSession, String target, String source, String messageId) throws PathNotFoundException, RepositoryException;
  
}
