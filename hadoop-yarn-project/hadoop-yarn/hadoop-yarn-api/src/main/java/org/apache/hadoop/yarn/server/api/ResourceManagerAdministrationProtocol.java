/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.apache.hadoop.yarn.server.api;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Evolving;
import org.apache.hadoop.io.retry.Idempotent;
import org.apache.hadoop.ipc.StandbyException;
import org.apache.hadoop.tools.GetUserMappingsProtocol;
import org.apache.hadoop.yarn.api.records.NodeId;
import org.apache.hadoop.yarn.api.records.ResourceOption;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.server.api.protocolrecords.AddToClusterNodeLabelsRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.AddToClusterNodeLabelsResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.NodesToAttributesMappingRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.NodesToAttributesMappingResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.CheckForDecommissioningNodesRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.CheckForDecommissioningNodesResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshAdminAclsRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshAdminAclsResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshClusterMaxPriorityRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshClusterMaxPriorityResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshNodesRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshNodesResourcesRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshNodesResourcesResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshNodesResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshQueuesRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshQueuesResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshServiceAclsRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshServiceAclsResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshSuperUserGroupsConfigurationRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshSuperUserGroupsConfigurationResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshUserToGroupsMappingsRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RefreshUserToGroupsMappingsResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.RemoveFromClusterNodeLabelsRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.RemoveFromClusterNodeLabelsResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.ReplaceLabelsOnNodeRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.ReplaceLabelsOnNodeResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.UpdateNodeResourceRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.UpdateNodeResourceResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.DeregisterSubClusterRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.DeregisterSubClusterResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.SaveFederationQueuePolicyRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.SaveFederationQueuePolicyResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.BatchSaveFederationQueuePoliciesRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.BatchSaveFederationQueuePoliciesResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.QueryFederationQueuePoliciesRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.QueryFederationQueuePoliciesResponse;
import org.apache.hadoop.yarn.server.api.protocolrecords.DeleteFederationApplicationRequest;
import org.apache.hadoop.yarn.server.api.protocolrecords.DeleteFederationApplicationResponse;

@Private
public interface ResourceManagerAdministrationProtocol extends GetUserMappingsProtocol {

  @Private
  @Idempotent
  public RefreshQueuesResponse refreshQueues(RefreshQueuesRequest request) 
  throws StandbyException, YarnException, IOException;

  @Private
  @Idempotent
  public RefreshNodesResponse refreshNodes(RefreshNodesRequest request)
  throws StandbyException, YarnException, IOException;

  @Private
  @Idempotent
  public RefreshSuperUserGroupsConfigurationResponse 
  refreshSuperUserGroupsConfiguration(
      RefreshSuperUserGroupsConfigurationRequest request)
  throws StandbyException, YarnException, IOException;

  @Private
  @Idempotent
  RefreshUserToGroupsMappingsResponse refreshUserToGroupsMappings(
      RefreshUserToGroupsMappingsRequest request)
  throws StandbyException, YarnException, IOException;

  @Private
  @Idempotent
  public RefreshAdminAclsResponse refreshAdminAcls(
      RefreshAdminAclsRequest request)
  throws YarnException, IOException;

  @Private
  @Idempotent
  public RefreshServiceAclsResponse refreshServiceAcls(
      RefreshServiceAclsRequest request)
  throws YarnException, IOException;
  
  /**
   * <p>The interface used by admin to update nodes' resources to the
   * <code>ResourceManager</code> </p>.
   * 
   * <p>The admin client is required to provide details such as a map from 
   * {@link NodeId} to {@link ResourceOption} required to update resources on 
   * a list of <code>RMNode</code> in <code>ResourceManager</code> etc.
   * via the {@link UpdateNodeResourceRequest}.</p>
   * 
   * @param request request to update resource for a node in cluster.
   * @return (empty) response on accepting update.
   * @throws YarnException exceptions from yarn servers.
   * @throws IOException io error occur.
   */
  @Private
  @Idempotent
  public UpdateNodeResourceResponse updateNodeResource(
      UpdateNodeResourceRequest request) throws YarnException, IOException;

  @Private
  @Evolving
  @Idempotent
  public RefreshNodesResourcesResponse refreshNodesResources(
      RefreshNodesResourcesRequest request) throws YarnException, IOException;

  @Private
  @Idempotent
  public AddToClusterNodeLabelsResponse addToClusterNodeLabels(
      AddToClusterNodeLabelsRequest request) throws YarnException, IOException;
   
  @Private
  @Idempotent
  public RemoveFromClusterNodeLabelsResponse removeFromClusterNodeLabels(
      RemoveFromClusterNodeLabelsRequest request) throws YarnException, IOException;
  
  @Private
  @Idempotent
  public ReplaceLabelsOnNodeResponse replaceLabelsOnNode(
      ReplaceLabelsOnNodeRequest request) throws YarnException, IOException;
  
  @Private
  @Idempotent
  public CheckForDecommissioningNodesResponse checkForDecommissioningNodes(
      CheckForDecommissioningNodesRequest checkForDecommissioningNodesRequest)
      throws YarnException, IOException;

  @Private
  @Idempotent
  public RefreshClusterMaxPriorityResponse refreshClusterMaxPriority(
      RefreshClusterMaxPriorityRequest request) throws YarnException,
      IOException;


  @Private
  @Idempotent
  NodesToAttributesMappingResponse mapAttributesToNodes(
      NodesToAttributesMappingRequest request) throws YarnException,
      IOException;

  /**
   * In YARN Federation mode, We allow users to mark subClusters
   * With no heartbeat for a long time as SC_LOST state.
   *
   * If we include a specific subClusterId in the request, check for the specified subCluster.
   * If subClusterId is empty, all subClusters are checked.
   *
   * @param request deregisterSubCluster request.
   * The request contains the id of to deregister sub-cluster.
   * @return Response from deregisterSubCluster.
   * @throws YarnException exceptions from yarn servers.
   * @throws IOException if an IO error occurred.
   */
  @Private
  @Idempotent
  DeregisterSubClusterResponse deregisterSubCluster(DeregisterSubClusterRequest request)
      throws YarnException, IOException;

  /**
   * In YARN-Federation mode, We will be storing the Policy information for Queues.
   *
   * @param request saveFederationQueuePolicy Request
   * @return Response from saveFederationQueuePolicy.
   * @throws YarnException exceptions from yarn servers.
   * @throws IOException if an IO error occurred.
   */
  @Private
  @Idempotent
  SaveFederationQueuePolicyResponse saveFederationQueuePolicy(
      SaveFederationQueuePolicyRequest request) throws YarnException, IOException;

  /**
   * In YARN-Federation mode, this method provides a way to save queue policies in batches.
   *
   * @param request BatchSaveFederationQueuePolicies Request.
   * @return Response from batchSaveFederationQueuePolicies.
   * @throws YarnException exceptions from yarn servers.
   * @throws IOException if an IO error occurred.
   */
  @Private
  @Idempotent
  BatchSaveFederationQueuePoliciesResponse batchSaveFederationQueuePolicies(
      BatchSaveFederationQueuePoliciesRequest request) throws YarnException, IOException;

  /**
   *  In YARN-Federation mode, this method provides a way to list policies.
   *
   * @param request QueryFederationQueuePoliciesRequest Request.
   * @return Response from listFederationQueuePolicies.
   * @throws YarnException exceptions from yarn servers.
   * @throws IOException if an IO error occurred.
   */
  @Private
  @Idempotent
  QueryFederationQueuePoliciesResponse listFederationQueuePolicies(
      QueryFederationQueuePoliciesRequest request) throws YarnException, IOException;

  /**
   * In YARN-Federation mode, this method provides a way to delete federation application.
   *
   * @param request DeleteFederationApplicationRequest Request.
   * @return Response from deleteFederationApplication.
   * @throws YarnException exceptions from yarn servers.
   * @throws IOException if an IO error occurred.
   */
  @Private
  @Idempotent
  DeleteFederationApplicationResponse deleteFederationApplication(
      DeleteFederationApplicationRequest request) throws YarnException, IOException;
}
