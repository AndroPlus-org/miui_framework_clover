// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.*;
import com.android.internal.telephony.ICarrierConfigLoader;

// Referenced classes of package android.telephony:
//            SubscriptionManager, Rlog

public class CarrierConfigManager
{

    public CarrierConfigManager()
    {
    }

    public static PersistableBundle getDefaultConfig()
    {
        return new PersistableBundle(sDefaults);
    }

    private ICarrierConfigLoader getICarrierConfigLoader()
    {
        return com.android.internal.telephony.ICarrierConfigLoader.Stub.asInterface(ServiceManager.getService("carrier_config"));
    }

    public PersistableBundle getConfig()
    {
        return getConfigForSubId(SubscriptionManager.getDefaultSubscriptionId());
    }

    public PersistableBundle getConfigForSubId(int i)
    {
        Object obj;
        try
        {
            obj = getICarrierConfigLoader();
        }
        catch(RemoteException remoteexception)
        {
            Rlog.e("CarrierConfigManager", (new StringBuilder()).append("Error getting config for subId ").append(i).append(": ").append(remoteexception.toString()).toString());
            return null;
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_46;
        obj = JVM INSTR new #741 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Rlog.w("CarrierConfigManager", ((StringBuilder) (obj)).append("Error getting config for subId ").append(i).append(" ICarrierConfigLoader is null").toString());
        return null;
        obj = ((ICarrierConfigLoader) (obj)).getConfigForSubId(i);
        return ((PersistableBundle) (obj));
    }

    public String getDefaultCarrierServicePackageName()
    {
        String s;
        try
        {
            s = getICarrierConfigLoader().getDefaultCarrierServicePackageName();
        }
        catch(Throwable throwable)
        {
            return null;
        }
        return s;
    }

    public void notifyConfigChangedForSubId(int i)
    {
        Object obj = getICarrierConfigLoader();
        if(obj == null)
        {
            try
            {
                obj = JVM INSTR new #741 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                Rlog.w("CarrierConfigManager", ((StringBuilder) (obj)).append("Error reloading config for subId=").append(i).append(" ICarrierConfigLoader is null").toString());
                return;
            }
            catch(RemoteException remoteexception)
            {
                Rlog.e("CarrierConfigManager", (new StringBuilder()).append("Error reloading config for subId=").append(i).append(": ").append(remoteexception.toString()).toString());
            }
            break MISSING_BLOCK_LABEL_52;
        }
        ((ICarrierConfigLoader) (obj)).notifyConfigChangedForSubId(i);
    }

    public void updateConfigForPhoneId(int i, String s)
    {
        ICarrierConfigLoader icarrierconfigloader = getICarrierConfigLoader();
        if(icarrierconfigloader == null)
        {
            try
            {
                s = JVM INSTR new #741 <Class StringBuilder>;
                s.StringBuilder();
                Rlog.w("CarrierConfigManager", s.append("Error updating config for phoneId=").append(i).append(" ICarrierConfigLoader is null").toString());
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Rlog.e("CarrierConfigManager", (new StringBuilder()).append("Error updating config for phoneId=").append(i).append(": ").append(s.toString()).toString());
            }
            break MISSING_BLOCK_LABEL_53;
        }
        icarrierconfigloader.updateConfigForPhoneId(i, s);
    }

    public static final String ACTION_CARRIER_CONFIG_CHANGED = "android.telephony.action.CARRIER_CONFIG_CHANGED";
    public static final int CDMA_ROAMING_MODE_AFFILIATED = 1;
    public static final int CDMA_ROAMING_MODE_ANY = 2;
    public static final int CDMA_ROAMING_MODE_HOME = 0;
    public static final int CDMA_ROAMING_MODE_RADIO_DEFAULT = -1;
    public static final int DATA_CYCLE_THRESHOLD_DISABLED = -2;
    public static final int DATA_CYCLE_USE_PLATFORM_DEFAULT = -1;
    public static final String IMSI_KEY_AVAILABILITY_INT = "imsi_key_availability_int";
    public static final String IMSI_KEY_DOWNLOAD_URL_STRING = "imsi_key_download_url_string";
    public static final String KEY_ADDITIONAL_CALL_SETTING_BOOL = "additional_call_setting_bool";
    public static final String KEY_ALLOW_ADDING_APNS_BOOL = "allow_adding_apns_bool";
    public static final String KEY_ALLOW_ADD_CALL_DURING_VIDEO_CALL_BOOL = "allow_add_call_during_video_call";
    public static final String KEY_ALLOW_EMERGENCY_NUMBERS_IN_CALL_LOG_BOOL = "allow_emergency_numbers_in_call_log_bool";
    public static final String KEY_ALLOW_EMERGENCY_VIDEO_CALLS_BOOL = "allow_emergency_video_calls_bool";
    public static final String KEY_ALLOW_HOLDING_VIDEO_CALL_BOOL = "allow_holding_video_call";
    public static final String KEY_ALLOW_HOLD_IN_IMS_CALL_BOOL = "allow_hold_in_ims_call";
    public static final String KEY_ALLOW_LOCAL_DTMF_TONES_BOOL = "allow_local_dtmf_tones_bool";
    public static final String KEY_ALLOW_MERGE_WIFI_CALLS_WHEN_VOWIFI_OFF_BOOL = "allow_merge_wifi_calls_when_vowifi_off_bool";
    public static final String KEY_ALLOW_NON_EMERGENCY_CALLS_IN_ECM_BOOL = "allow_non_emergency_calls_in_ecm_bool";
    public static final String KEY_ALLOW_USSD_REQUESTS_VIA_TELEPHONY_MANAGER_BOOL = "allow_ussd_requests_via_telephony_manager_bool";
    public static final String KEY_ALLOW_VIDEO_CALLING_FALLBACK_BOOL = "allow_video_calling_fallback_bool";
    public static final String KEY_ALWAYS_SHOW_EMERGENCY_ALERT_ONOFF_BOOL = "always_show_emergency_alert_onoff_bool";
    public static final String KEY_APN_EXPAND_BOOL = "apn_expand_bool";
    public static final String KEY_AUTO_RETRY_ENABLED_BOOL = "auto_retry_enabled_bool";
    public static final String KEY_BOOSTED_LTE_EARFCNS_STRING_ARRAY = "boosted_lte_earfcns_string_array";
    public static final String KEY_BROADCAST_EMERGENCY_CALL_STATE_CHANGES_BOOL = "broadcast_emergency_call_state_changes_bool";
    public static final String KEY_CALL_FORWARDING_BLOCKS_WHILE_ROAMING_STRING_ARRAY = "call_forwarding_blocks_while_roaming_string_array";
    public static final String KEY_CALL_FORWARDING_MAP_NON_NUMBER_TO_VOICEMAIL_BOOL = "call_forwarding_map_non_number_to_voicemail_bool";
    public static final String KEY_CARRIER_ALLOW_TURNOFF_IMS_BOOL = "carrier_allow_turnoff_ims_bool";
    public static final String KEY_CARRIER_APP_NO_WAKE_SIGNAL_CONFIG_STRING_ARRAY = "carrier_app_no_wake_signal_config";
    public static final String KEY_CARRIER_APP_WAKE_SIGNAL_CONFIG_STRING_ARRAY = "carrier_app_wake_signal_config";
    public static final String KEY_CARRIER_DATA_CALL_APN_DELAY_DEFAULT_LONG = "carrier_data_call_apn_delay_default_long";
    public static final String KEY_CARRIER_DATA_CALL_APN_DELAY_FASTER_LONG = "carrier_data_call_apn_delay_faster_long";
    public static final String KEY_CARRIER_DATA_CALL_APN_RETRY_AFTER_DISCONNECT_LONG = "carrier_data_call_apn_retry_after_disconnect_long";
    public static final String KEY_CARRIER_DATA_CALL_PERMANENT_FAILURE_STRINGS = "carrier_data_call_permanent_failure_strings";
    public static final String KEY_CARRIER_DATA_CALL_RETRY_CONFIG_STRINGS = "carrier_data_call_retry_config_strings";
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_DCFAILURE_STRING_ARRAY = "carrier_default_actions_on_dcfailure_string_array";
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_DEFAULT_NETWORK_AVAILABLE = "carrier_default_actions_on_default_network_available_string_array";
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_REDIRECTION_STRING_ARRAY = "carrier_default_actions_on_redirection_string_array";
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_RESET = "carrier_default_actions_on_reset_string_array";
    public static final String KEY_CARRIER_DEFAULT_DATA_ROAMING_ENABLED_BOOL = "carrier_default_data_roaming_enabled_bool";
    public static final String KEY_CARRIER_DEFAULT_REDIRECTION_URL_STRING_ARRAY = "carrier_default_redirection_url_string_array";
    public static final String KEY_CARRIER_DEFAULT_WFC_IMS_ENABLED_BOOL = "carrier_default_wfc_ims_enabled_bool";
    public static final String KEY_CARRIER_DEFAULT_WFC_IMS_MODE_INT = "carrier_default_wfc_ims_mode_int";
    public static final String KEY_CARRIER_DEFAULT_WFC_IMS_ROAMING_ENABLED_BOOL = "carrier_default_wfc_ims_roaming_enabled_bool";
    public static final String KEY_CARRIER_DEFAULT_WFC_IMS_ROAMING_MODE_INT = "carrier_default_wfc_ims_roaming_mode_int";
    public static final String KEY_CARRIER_ERI_FILE_NAME_STRING = "carrier_eri_file_name_string";
    public static final String KEY_CARRIER_FORCE_DISABLE_ETWS_CMAS_TEST_BOOL = "carrier_force_disable_etws_cmas_test_bool";
    public static final String KEY_CARRIER_IMS_GBA_REQUIRED_BOOL = "carrier_ims_gba_required_bool";
    public static final String KEY_CARRIER_INSTANT_LETTERING_AVAILABLE_BOOL = "carrier_instant_lettering_available_bool";
    public static final String KEY_CARRIER_INSTANT_LETTERING_ENCODING_STRING = "carrier_instant_lettering_encoding_string";
    public static final String KEY_CARRIER_INSTANT_LETTERING_ESCAPED_CHARS_STRING = "carrier_instant_lettering_escaped_chars_string";
    public static final String KEY_CARRIER_INSTANT_LETTERING_INVALID_CHARS_STRING = "carrier_instant_lettering_invalid_chars_string";
    public static final String KEY_CARRIER_INSTANT_LETTERING_LENGTH_LIMIT_INT = "carrier_instant_lettering_length_limit_int";
    public static final String KEY_CARRIER_METERED_APN_TYPES_STRINGS = "carrier_metered_apn_types_strings";
    public static final String KEY_CARRIER_METERED_IWLAN_APN_TYPES_STRINGS = "carrier_metered_iwlan_apn_types_strings";
    public static final String KEY_CARRIER_METERED_ROAMING_APN_TYPES_STRINGS = "carrier_metered_roaming_apn_types_strings";
    public static final String KEY_CARRIER_NAME_OVERRIDE_BOOL = "carrier_name_override_bool";
    public static final String KEY_CARRIER_NAME_STRING = "carrier_name_string";
    public static final String KEY_CARRIER_PROMOTE_WFC_ON_CALL_FAIL_BOOL = "carrier_promote_wfc_on_call_fail_bool";
    public static final String KEY_CARRIER_SETTINGS_ENABLE_BOOL = "carrier_settings_enable_bool";
    public static final String KEY_CARRIER_SETUP_APP_STRING = "carrier_setup_app_string";
    public static final String KEY_CARRIER_USE_IMS_FIRST_FOR_EMERGENCY_BOOL = "carrier_use_ims_first_for_emergency_bool";
    public static final String KEY_CARRIER_VOLTE_AVAILABLE_BOOL = "carrier_volte_available_bool";
    public static final String KEY_CARRIER_VOLTE_OVERRIDE_WFC_PROVISIONING_BOOL = "carrier_volte_override_wfc_provisioning_bool";
    public static final String KEY_CARRIER_VOLTE_PROVISIONED_BOOL = "carrier_volte_provisioned_bool";
    public static final String KEY_CARRIER_VOLTE_PROVISIONING_REQUIRED_BOOL = "carrier_volte_provisioning_required_bool";
    public static final String KEY_CARRIER_VOLTE_TTY_SUPPORTED_BOOL = "carrier_volte_tty_supported_bool";
    public static final String KEY_CARRIER_VT_AVAILABLE_BOOL = "carrier_vt_available_bool";
    public static final String KEY_CARRIER_VVM_PACKAGE_NAME_STRING = "carrier_vvm_package_name_string";
    public static final String KEY_CARRIER_VVM_PACKAGE_NAME_STRING_ARRAY = "carrier_vvm_package_name_string_array";
    public static final String KEY_CARRIER_WFC_IMS_AVAILABLE_BOOL = "carrier_wfc_ims_available_bool";
    public static final String KEY_CARRIER_WFC_SUPPORTS_WIFI_ONLY_BOOL = "carrier_wfc_supports_wifi_only_bool";
    public static final String KEY_CARRIER_WIFI_STRING_ARRAY = "carrier_wifi_string_array";
    public static final String KEY_CDMA_3WAYCALL_FLASH_DELAY_INT = "cdma_3waycall_flash_delay_int";
    public static final String KEY_CDMA_CW_CF_ENABLED_BOOL = "cdma_cw_cf_enabled_bool";
    public static final String KEY_CDMA_DTMF_TONE_DELAY_INT = "cdma_dtmf_tone_delay_int";
    public static final String KEY_CDMA_NONROAMING_NETWORKS_STRING_ARRAY = "cdma_nonroaming_networks_string_array";
    public static final String KEY_CDMA_ROAMING_MODE_INT = "cdma_roaming_mode_int";
    public static final String KEY_CDMA_ROAMING_NETWORKS_STRING_ARRAY = "cdma_roaming_networks_string_array";
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_BOOL = "ci_action_on_sys_update_bool";
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_EXTRA_STRING = "ci_action_on_sys_update_extra_string";
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_EXTRA_VAL_STRING = "ci_action_on_sys_update_extra_val_string";
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_INTENT_STRING = "ci_action_on_sys_update_intent_string";
    public static final String KEY_CONFIG_IMS_PACKAGE_OVERRIDE_STRING = "config_ims_package_override_string";
    public static final String KEY_CONFIG_PLANS_PACKAGE_OVERRIDE_STRING = "config_plans_package_override_string";
    public static final String KEY_CONFIG_WIFI_DISABLE_IN_ECBM = "config_wifi_disable_in_ecbm";
    public static final String KEY_CONVERT_CDMA_CALLER_ID_MMI_CODES_WHILE_ROAMING_ON_3GPP_BOOL = "convert_cdma_caller_id_mmi_codes_while_roaming_on_3gpp_bool";
    public static final String KEY_CSP_ENABLED_BOOL = "csp_enabled_bool";
    public static final String KEY_DATA_LIMIT_THRESHOLD_BYTES_LONG = "data_limit_threshold_bytes_long";
    public static final String KEY_DATA_WARNING_THRESHOLD_BYTES_LONG = "data_warning_threshold_bytes_long";
    public static final String KEY_DEFAULT_SIM_CALL_MANAGER_STRING = "default_sim_call_manager_string";
    public static final String KEY_DEFAULT_VM_NUMBER_STRING = "default_vm_number_string";
    public static final String KEY_DIAL_STRING_REPLACE_STRING_ARRAY = "dial_string_replace_string_array";
    public static final String KEY_DISABLE_CDMA_ACTIVATION_CODE_BOOL = "disable_cdma_activation_code_bool";
    public static final String KEY_DISABLE_SEVERE_WHEN_EXTREME_DISABLED_BOOL = "disable_severe_when_extreme_disabled_bool";
    public static final String KEY_DISABLE_VOICE_BARRING_NOTIFICATION_BOOL = "disable_voice_barring_notification_bool";
    public static final String KEY_DISPLAY_HD_AUDIO_PROPERTY_BOOL = "display_hd_audio_property_bool";
    public static final String KEY_DROP_VIDEO_CALL_WHEN_ANSWERING_AUDIO_CALL_BOOL = "drop_video_call_when_answering_audio_call_bool";
    public static final String KEY_DTMF_TYPE_ENABLED_BOOL = "dtmf_type_enabled_bool";
    public static final String KEY_DURATION_BLOCKING_DISABLED_AFTER_EMERGENCY_INT = "duration_blocking_disabled_after_emergency_int";
    public static final String KEY_EDITABLE_ENHANCED_4G_LTE_BOOL = "editable_enhanced_4g_lte_bool";
    public static final String KEY_EDITABLE_VOICEMAIL_NUMBER_BOOL = "editable_voicemail_number_bool";
    public static final String KEY_EDITABLE_WFC_MODE_BOOL = "editable_wfc_mode_bool";
    public static final String KEY_EDITABLE_WFC_ROAMING_MODE_BOOL = "editable_wfc_roaming_mode_bool";
    public static final String KEY_EMERGENCY_NOTIFICATION_DELAY_INT = "emergency_notification_delay_int";
    public static final String KEY_ENABLE_APPS_STRING_ARRAY = "enable_apps_string_array";
    public static final String KEY_ENABLE_DIALER_KEY_VIBRATION_BOOL = "enable_dialer_key_vibration_bool";
    public static final String KEY_ENHANCED_4G_LTE_TITLE_VARIANT_BOOL = "enhanced_4g_lte_title_variant_bool";
    public static final String KEY_FILTERED_CNAP_NAMES_STRING_ARRAY = "filtered_cnap_names_string_array";
    public static final String KEY_FORCE_HOME_NETWORK_BOOL = "force_home_network_bool";
    public static final String KEY_FORCE_IMEI_BOOL = "force_imei_bool";
    public static final String KEY_GSM_DTMF_TONE_DELAY_INT = "gsm_dtmf_tone_delay_int";
    public static final String KEY_GSM_NONROAMING_NETWORKS_STRING_ARRAY = "gsm_nonroaming_networks_string_array";
    public static final String KEY_GSM_ROAMING_NETWORKS_STRING_ARRAY = "gsm_roaming_networks_string_array";
    public static final String KEY_HAS_IN_CALL_NOISE_SUPPRESSION_BOOL = "has_in_call_noise_suppression_bool";
    public static final String KEY_HIDE_CARRIER_NETWORK_SETTINGS_BOOL = "hide_carrier_network_settings_bool";
    public static final String KEY_HIDE_ENHANCED_4G_LTE_BOOL = "hide_enhanced_4g_lte_bool";
    public static final String KEY_HIDE_IMS_APN_BOOL = "hide_ims_apn_bool";
    public static final String KEY_HIDE_PREFERRED_NETWORK_TYPE_BOOL = "hide_preferred_network_type_bool";
    public static final String KEY_HIDE_SIM_LOCK_SETTINGS_BOOL = "hide_sim_lock_settings_bool";
    public static final String KEY_IGNORE_DATA_ENABLED_CHANGED_FOR_VIDEO_CALLS = "ignore_data_enabled_changed_for_video_calls";
    public static final String KEY_IGNORE_RESET_UT_CAPABILITY_BOOL = "ignore_reset_ut_capability_bool";
    public static final String KEY_IGNORE_SIM_NETWORK_LOCKED_EVENTS_BOOL = "ignore_sim_network_locked_events_bool";
    public static final String KEY_IMS_CONFERENCE_SIZE_LIMIT_INT = "ims_conference_size_limit_int";
    public static final String KEY_IMS_DTMF_TONE_DELAY_INT = "ims_dtmf_tone_delay_int";
    public static final String KEY_IMS_REASONINFO_MAPPING_STRING_ARRAY = "ims_reasoninfo_mapping_string_array";
    public static final String KEY_IS_IMS_CONFERENCE_SIZE_ENFORCED_BOOL = "is_ims_conference_size_enforced_bool";
    public static final String KEY_LTE_EARFCNS_RSRP_BOOST_INT = "lte_earfcns_rsrp_boost_int";
    public static final String KEY_MDN_IS_ADDITIONAL_VOICEMAIL_NUMBER_BOOL = "mdn_is_additional_voicemail_number_bool";
    public static final String KEY_MESSAGE_EXPIRATION_TIME_LONG = "message_expiration_time_long";
    public static final String KEY_MMS_ALIAS_ENABLED_BOOL = "aliasEnabled";
    public static final String KEY_MMS_ALIAS_MAX_CHARS_INT = "aliasMaxChars";
    public static final String KEY_MMS_ALIAS_MIN_CHARS_INT = "aliasMinChars";
    public static final String KEY_MMS_ALLOW_ATTACH_AUDIO_BOOL = "allowAttachAudio";
    public static final String KEY_MMS_APPEND_TRANSACTION_ID_BOOL = "enabledTransID";
    public static final String KEY_MMS_CLOSE_CONNECTION_BOOL = "mmsCloseConnection";
    public static final String KEY_MMS_EMAIL_GATEWAY_NUMBER_STRING = "emailGatewayNumber";
    public static final String KEY_MMS_GROUP_MMS_ENABLED_BOOL = "enableGroupMms";
    public static final String KEY_MMS_HTTP_PARAMS_STRING = "httpParams";
    public static final String KEY_MMS_HTTP_SOCKET_TIMEOUT_INT = "httpSocketTimeout";
    public static final String KEY_MMS_MAX_IMAGE_HEIGHT_INT = "maxImageHeight";
    public static final String KEY_MMS_MAX_IMAGE_WIDTH_INT = "maxImageWidth";
    public static final String KEY_MMS_MAX_MESSAGE_SIZE_INT = "maxMessageSize";
    public static final String KEY_MMS_MESSAGE_TEXT_MAX_SIZE_INT = "maxMessageTextSize";
    public static final String KEY_MMS_MMS_DELIVERY_REPORT_ENABLED_BOOL = "enableMMSDeliveryReports";
    public static final String KEY_MMS_MMS_ENABLED_BOOL = "enabledMMS";
    public static final String KEY_MMS_MMS_READ_REPORT_ENABLED_BOOL = "enableMMSReadReports";
    public static final String KEY_MMS_MULTIPART_SMS_ENABLED_BOOL = "enableMultipartSMS";
    public static final String KEY_MMS_NAI_SUFFIX_STRING = "naiSuffix";
    public static final String KEY_MMS_NOTIFY_WAP_MMSC_ENABLED_BOOL = "enabledNotifyWapMMSC";
    public static final String KEY_MMS_RECIPIENT_LIMIT_INT = "recipientLimit";
    public static final String KEY_MMS_SEND_MULTIPART_SMS_AS_SEPARATE_MESSAGES_BOOL = "sendMultipartSmsAsSeparateMessages";
    public static final String KEY_MMS_SHOW_CELL_BROADCAST_APP_LINKS_BOOL = "config_cellBroadcastAppLinks";
    public static final String KEY_MMS_SMS_DELIVERY_REPORT_ENABLED_BOOL = "enableSMSDeliveryReports";
    public static final String KEY_MMS_SMS_TO_MMS_TEXT_LENGTH_THRESHOLD_INT = "smsToMmsTextLengthThreshold";
    public static final String KEY_MMS_SMS_TO_MMS_TEXT_THRESHOLD_INT = "smsToMmsTextThreshold";
    public static final String KEY_MMS_SUBJECT_MAX_LENGTH_INT = "maxSubjectLength";
    public static final String KEY_MMS_SUPPORT_HTTP_CHARSET_HEADER_BOOL = "supportHttpCharsetHeader";
    public static final String KEY_MMS_SUPPORT_MMS_CONTENT_DISPOSITION_BOOL = "supportMmsContentDisposition";
    public static final String KEY_MMS_UA_PROF_TAG_NAME_STRING = "uaProfTagName";
    public static final String KEY_MMS_UA_PROF_URL_STRING = "uaProfUrl";
    public static final String KEY_MMS_USER_AGENT_STRING = "userAgent";
    public static final String KEY_MONTHLY_DATA_CYCLE_DAY_INT = "monthly_data_cycle_day_int";
    public static final String KEY_NON_ROAMING_OPERATOR_STRING_ARRAY = "non_roaming_operator_string_array";
    public static final String KEY_NOTIFY_HANDOVER_VIDEO_FROM_WIFI_TO_LTE_BOOL = "notify_handover_video_from_wifi_to_lte_bool";
    public static final String KEY_NOTIFY_INTERNATIONAL_CALL_ON_WFC_BOOL = "notify_international_call_on_wfc_bool";
    public static final String KEY_NOTIFY_VT_HANDOVER_TO_WIFI_FAILURE_BOOL = "notify_vt_handover_to_wifi_failure_bool";
    public static final String KEY_ONLY_SINGLE_DC_ALLOWED_INT_ARRAY = "only_single_dc_allowed_int_array";
    public static final String KEY_OPERATOR_SELECTION_EXPAND_BOOL = "operator_selection_expand_bool";
    public static final String KEY_PERSIST_LPP_MODE_BOOL = "persist_lpp_mode_bool";
    public static final String KEY_PREFER_2G_BOOL = "prefer_2g_bool";
    public static final String KEY_PREF_NETWORK_NOTIFICATION_DELAY_INT = "network_notification_delay_int";
    public static final String KEY_RATCHET_RAT_FAMILIES = "ratchet_rat_families";
    public static final String KEY_RCS_CONFIG_SERVER_URL_STRING = "rcs_config_server_url_string";
    public static final String KEY_READ_ONLY_APN_FIELDS_STRING_ARRAY = "read_only_apn_fields_string_array";
    public static final String KEY_READ_ONLY_APN_TYPES_STRING_ARRAY = "read_only_apn_types_string_array";
    public static final String KEY_REQUIRE_ENTITLEMENT_CHECKS_BOOL = "require_entitlement_checks_bool";
    public static final String KEY_RESTART_RADIO_ON_PDP_FAIL_REGULAR_DEACTIVATION_BOOL = "restart_radio_on_pdp_fail_regular_deactivation_bool";
    public static final String KEY_ROAMING_OPERATOR_STRING_ARRAY = "roaming_operator_string_array";
    public static final String KEY_SHOW_APN_SETTING_CDMA_BOOL = "show_apn_setting_cdma_bool";
    public static final String KEY_SHOW_CDMA_CHOICES_BOOL = "show_cdma_choices_bool";
    public static final String KEY_SHOW_ICCID_IN_SIM_STATUS_BOOL = "show_iccid_in_sim_status_bool";
    public static final String KEY_SHOW_ONSCREEN_DIAL_BUTTON_BOOL = "show_onscreen_dial_button_bool";
    public static final String KEY_SIMPLIFIED_NETWORK_SETTINGS_BOOL = "simplified_network_settings_bool";
    public static final String KEY_SIM_NETWORK_UNLOCK_ALLOW_DISMISS_BOOL = "sim_network_unlock_allow_dismiss_bool";
    public static final String KEY_SMS_REQUIRES_DESTINATION_NUMBER_CONVERSION_BOOL = "sms_requires_destination_number_conversion_bool";
    public static final String KEY_STK_DISABLE_LAUNCH_BROWSER_BOOL = "stk_disable_launch_browser_bool";
    public static final String KEY_SUPPORT_3GPP_CALL_FORWARDING_WHILE_ROAMING_BOOL = "support_3gpp_call_forwarding_while_roaming_bool";
    public static final String KEY_SUPPORT_CONFERENCE_CALL_BOOL = "support_conference_call_bool";
    public static final String KEY_SUPPORT_DIRECT_FDN_DIALING_BOOL = "support_direct_fdn_dialing_bool";
    public static final String KEY_SUPPORT_DOWNGRADE_VT_TO_AUDIO_BOOL = "support_downgrade_vt_to_audio_bool";
    public static final String KEY_SUPPORT_IMS_CONFERENCE_CALL_BOOL = "support_ims_conference_call_bool";
    public static final String KEY_SUPPORT_PAUSE_IMS_VIDEO_CALLS_BOOL = "support_pause_ims_video_calls_bool";
    public static final String KEY_SUPPORT_SWAP_AFTER_MERGE_BOOL = "support_swap_after_merge_bool";
    public static final String KEY_SUPPORT_VIDEO_CONFERENCE_CALL_BOOL = "support_video_conference_call_bool";
    public static final String KEY_TREAT_DOWNGRADED_VIDEO_CALLS_AS_VIDEO_CALLS_BOOL = "treat_downgraded_video_calls_as_video_calls_bool";
    public static final String KEY_USE_HFA_FOR_PROVISIONING_BOOL = "use_hfa_for_provisioning_bool";
    public static final String KEY_USE_OTASP_FOR_PROVISIONING_BOOL = "use_otasp_for_provisioning_bool";
    public static final String KEY_USE_RCS_PRESENCE_BOOL = "use_rcs_presence_bool";
    public static final String KEY_VIDEO_CALLS_CAN_BE_HD_AUDIO = "video_calls_can_be_hd_audio";
    public static final String KEY_VILTE_DATA_IS_METERED_BOOL = "vilte_data_is_metered_bool";
    public static final String KEY_VOICEMAIL_NOTIFICATION_PERSISTENT_BOOL = "voicemail_notification_persistent_bool";
    public static final String KEY_VOICE_PRIVACY_DISABLE_UI_BOOL = "voice_privacy_disable_ui_bool";
    public static final String KEY_VOLTE_REPLACEMENT_RAT_INT = "volte_replacement_rat_int";
    public static final String KEY_VVM_CELLULAR_DATA_REQUIRED_BOOL = "vvm_cellular_data_required_bool";
    public static final String KEY_VVM_CLIENT_PREFIX_STRING = "vvm_client_prefix_string";
    public static final String KEY_VVM_DESTINATION_NUMBER_STRING = "vvm_destination_number_string";
    public static final String KEY_VVM_DISABLED_CAPABILITIES_STRING_ARRAY = "vvm_disabled_capabilities_string_array";
    public static final String KEY_VVM_LEGACY_MODE_ENABLED_BOOL = "vvm_legacy_mode_enabled_bool";
    public static final String KEY_VVM_PORT_NUMBER_INT = "vvm_port_number_int";
    public static final String KEY_VVM_PREFETCH_BOOL = "vvm_prefetch_bool";
    public static final String KEY_VVM_SSL_ENABLED_BOOL = "vvm_ssl_enabled_bool";
    public static final String KEY_VVM_TYPE_STRING = "vvm_type_string";
    public static final String KEY_WFC_DATA_SPN_FORMAT_IDX_INT = "wfc_data_spn_format_idx_int";
    public static final String KEY_WFC_EMERGENCY_ADDRESS_CARRIER_APP_STRING = "wfc_emergency_address_carrier_app_string";
    public static final String KEY_WFC_OPERATOR_ERROR_CODES_STRING_ARRAY = "wfc_operator_error_codes_string_array";
    public static final String KEY_WFC_SPN_FORMAT_IDX_INT = "wfc_spn_format_idx_int";
    public static final String KEY_WIFI_CALLS_CAN_BE_HD_AUDIO = "wifi_calls_can_be_hd_audio";
    public static final String KEY_WORLD_PHONE_BOOL = "world_phone_bool";
    private static final String TAG = "CarrierConfigManager";
    private static final PersistableBundle sDefaults;

    static 
    {
        sDefaults = new PersistableBundle();
        sDefaults.putBoolean("allow_hold_in_ims_call", true);
        sDefaults.putBoolean("additional_call_setting_bool", true);
        sDefaults.putBoolean("allow_emergency_numbers_in_call_log_bool", true);
        sDefaults.putBoolean("allow_local_dtmf_tones_bool", true);
        sDefaults.putBoolean("apn_expand_bool", true);
        sDefaults.putBoolean("auto_retry_enabled_bool", false);
        sDefaults.putBoolean("carrier_settings_enable_bool", false);
        sDefaults.putBoolean("carrier_volte_available_bool", false);
        sDefaults.putBoolean("carrier_vt_available_bool", false);
        sDefaults.putBoolean("notify_handover_video_from_wifi_to_lte_bool", false);
        sDefaults.putBoolean("support_downgrade_vt_to_audio_bool", true);
        sDefaults.putString("default_vm_number_string", "");
        sDefaults.putBoolean("ignore_data_enabled_changed_for_video_calls", true);
        sDefaults.putBoolean("vilte_data_is_metered_bool", true);
        sDefaults.putBoolean("ignore_reset_ut_capability_bool", false);
        sDefaults.putBoolean("carrier_wfc_ims_available_bool", false);
        sDefaults.putBoolean("carrier_wfc_supports_wifi_only_bool", false);
        sDefaults.putBoolean("carrier_default_wfc_ims_enabled_bool", false);
        sDefaults.putBoolean("carrier_default_wfc_ims_roaming_enabled_bool", false);
        sDefaults.putBoolean("carrier_promote_wfc_on_call_fail_bool", false);
        sDefaults.putInt("carrier_default_wfc_ims_mode_int", 2);
        sDefaults.putInt("carrier_default_wfc_ims_roaming_mode_int", 2);
        sDefaults.putBoolean("carrier_force_disable_etws_cmas_test_bool", false);
        sDefaults.putBoolean("carrier_volte_provisioning_required_bool", false);
        sDefaults.putBoolean("carrier_volte_override_wfc_provisioning_bool", false);
        sDefaults.putBoolean("carrier_volte_tty_supported_bool", true);
        sDefaults.putBoolean("carrier_allow_turnoff_ims_bool", true);
        sDefaults.putBoolean("carrier_ims_gba_required_bool", false);
        sDefaults.putBoolean("carrier_instant_lettering_available_bool", false);
        sDefaults.putBoolean("carrier_use_ims_first_for_emergency_bool", false);
        sDefaults.putString("carrier_instant_lettering_invalid_chars_string", "");
        sDefaults.putString("carrier_instant_lettering_escaped_chars_string", "");
        sDefaults.putString("carrier_instant_lettering_encoding_string", "");
        sDefaults.putInt("carrier_instant_lettering_length_limit_int", 64);
        sDefaults.putBoolean("disable_cdma_activation_code_bool", false);
        sDefaults.putBoolean("dtmf_type_enabled_bool", false);
        sDefaults.putBoolean("enable_dialer_key_vibration_bool", true);
        sDefaults.putBoolean("has_in_call_noise_suppression_bool", false);
        sDefaults.putBoolean("hide_carrier_network_settings_bool", false);
        sDefaults.putBoolean("simplified_network_settings_bool", false);
        sDefaults.putBoolean("hide_sim_lock_settings_bool", false);
        sDefaults.putBoolean("carrier_volte_provisioned_bool", false);
        sDefaults.putBoolean("ignore_sim_network_locked_events_bool", false);
        sDefaults.putBoolean("mdn_is_additional_voicemail_number_bool", false);
        sDefaults.putBoolean("operator_selection_expand_bool", true);
        sDefaults.putBoolean("prefer_2g_bool", true);
        sDefaults.putBoolean("show_apn_setting_cdma_bool", false);
        sDefaults.putBoolean("show_cdma_choices_bool", false);
        sDefaults.putBoolean("sms_requires_destination_number_conversion_bool", false);
        sDefaults.putBoolean("show_onscreen_dial_button_bool", true);
        sDefaults.putBoolean("sim_network_unlock_allow_dismiss_bool", true);
        sDefaults.putBoolean("support_pause_ims_video_calls_bool", false);
        sDefaults.putBoolean("support_swap_after_merge_bool", true);
        sDefaults.putBoolean("use_hfa_for_provisioning_bool", false);
        sDefaults.putBoolean("editable_voicemail_number_bool", false);
        sDefaults.putBoolean("use_otasp_for_provisioning_bool", false);
        sDefaults.putBoolean("voicemail_notification_persistent_bool", false);
        sDefaults.putBoolean("voice_privacy_disable_ui_bool", false);
        sDefaults.putBoolean("world_phone_bool", false);
        sDefaults.putBoolean("require_entitlement_checks_bool", true);
        sDefaults.putBoolean("restart_radio_on_pdp_fail_regular_deactivation_bool", false);
        sDefaults.putInt("volte_replacement_rat_int", 0);
        sDefaults.putString("default_sim_call_manager_string", "");
        sDefaults.putString("vvm_destination_number_string", "");
        sDefaults.putInt("vvm_port_number_int", 0);
        sDefaults.putString("vvm_type_string", "");
        sDefaults.putBoolean("vvm_cellular_data_required_bool", false);
        sDefaults.putString("vvm_client_prefix_string", "//VVM");
        sDefaults.putBoolean("vvm_ssl_enabled_bool", false);
        sDefaults.putStringArray("vvm_disabled_capabilities_string_array", null);
        sDefaults.putBoolean("vvm_legacy_mode_enabled_bool", false);
        sDefaults.putBoolean("vvm_prefetch_bool", true);
        sDefaults.putString("carrier_vvm_package_name_string", "");
        sDefaults.putStringArray("carrier_vvm_package_name_string_array", null);
        sDefaults.putBoolean("show_iccid_in_sim_status_bool", false);
        sDefaults.putBoolean("ci_action_on_sys_update_bool", false);
        sDefaults.putString("ci_action_on_sys_update_intent_string", "");
        sDefaults.putString("ci_action_on_sys_update_extra_string", "");
        sDefaults.putString("ci_action_on_sys_update_extra_val_string", "");
        sDefaults.putBoolean("csp_enabled_bool", false);
        sDefaults.putBoolean("allow_adding_apns_bool", true);
        sDefaults.putStringArray("read_only_apn_types_string_array", new String[] {
            "dun"
        });
        sDefaults.putStringArray("read_only_apn_fields_string_array", null);
        sDefaults.putBoolean("broadcast_emergency_call_state_changes_bool", false);
        sDefaults.putBoolean("always_show_emergency_alert_onoff_bool", false);
        sDefaults.putBoolean("disable_severe_when_extreme_disabled_bool", true);
        sDefaults.putLong("message_expiration_time_long", 0x5265c00L);
        sDefaults.putStringArray("carrier_data_call_retry_config_strings", new String[] {
            "default:default_randomization=2000,5000,10000,20000,40000,80000:5000,160000:5000,320000:5000,640000:5000,1280000:5000,1800000:5000", "mms:default_randomization=2000,5000,10000,20000,40000,80000:5000,160000:5000,320000:5000,640000:5000,1280000:5000,1800000:5000", "others:max_retries=3, 5000, 5000, 5000"
        });
        sDefaults.putLong("carrier_data_call_apn_delay_default_long", 20000L);
        sDefaults.putLong("carrier_data_call_apn_delay_faster_long", 3000L);
        sDefaults.putLong("carrier_data_call_apn_retry_after_disconnect_long", 10000L);
        sDefaults.putString("carrier_eri_file_name_string", "eri.xml");
        sDefaults.putInt("duration_blocking_disabled_after_emergency_int", 7200);
        sDefaults.putStringArray("carrier_metered_apn_types_strings", new String[] {
            "default", "mms", "dun", "supl"
        });
        sDefaults.putStringArray("carrier_metered_roaming_apn_types_strings", new String[] {
            "default", "mms", "dun", "supl"
        });
        sDefaults.putBoolean("cdma_cw_cf_enabled_bool", false);
        sDefaults.putStringArray("carrier_metered_iwlan_apn_types_strings", new String[0]);
        sDefaults.putIntArray("only_single_dc_allowed_int_array", new int[] {
            4, 5, 6, 7, 8, 12
        });
        sDefaults.putStringArray("gsm_roaming_networks_string_array", null);
        sDefaults.putStringArray("gsm_nonroaming_networks_string_array", null);
        sDefaults.putString("config_ims_package_override_string", null);
        sDefaults.putStringArray("cdma_roaming_networks_string_array", null);
        sDefaults.putStringArray("cdma_nonroaming_networks_string_array", null);
        sDefaults.putStringArray("dial_string_replace_string_array", null);
        sDefaults.putBoolean("force_home_network_bool", false);
        sDefaults.putInt("gsm_dtmf_tone_delay_int", 0);
        sDefaults.putInt("ims_dtmf_tone_delay_int", 0);
        sDefaults.putInt("cdma_dtmf_tone_delay_int", 100);
        sDefaults.putBoolean("call_forwarding_map_non_number_to_voicemail_bool", false);
        sDefaults.putInt("cdma_3waycall_flash_delay_int", 0);
        sDefaults.putBoolean("support_conference_call_bool", true);
        sDefaults.putBoolean("support_ims_conference_call_bool", true);
        sDefaults.putBoolean("support_video_conference_call_bool", false);
        sDefaults.putBoolean("is_ims_conference_size_enforced_bool", false);
        sDefaults.putInt("ims_conference_size_limit_int", 5);
        sDefaults.putBoolean("display_hd_audio_property_bool", true);
        sDefaults.putBoolean("editable_enhanced_4g_lte_bool", true);
        sDefaults.putBoolean("hide_enhanced_4g_lte_bool", false);
        sDefaults.putBoolean("hide_ims_apn_bool", true);
        sDefaults.putBoolean("hide_preferred_network_type_bool", false);
        sDefaults.putBoolean("allow_emergency_video_calls_bool", false);
        sDefaults.putStringArray("enable_apps_string_array", null);
        sDefaults.putBoolean("editable_wfc_mode_bool", true);
        sDefaults.putStringArray("wfc_operator_error_codes_string_array", null);
        sDefaults.putInt("wfc_spn_format_idx_int", 0);
        sDefaults.putInt("wfc_data_spn_format_idx_int", 0);
        sDefaults.putString("wfc_emergency_address_carrier_app_string", "");
        sDefaults.putBoolean("config_wifi_disable_in_ecbm", false);
        sDefaults.putBoolean("carrier_name_override_bool", false);
        sDefaults.putString("carrier_name_string", "");
        sDefaults.putBoolean("support_direct_fdn_dialing_bool", false);
        sDefaults.putBoolean("carrier_default_data_roaming_enabled_bool", false);
        sDefaults.putBoolean("aliasEnabled", false);
        sDefaults.putBoolean("allowAttachAudio", true);
        sDefaults.putBoolean("enabledTransID", false);
        sDefaults.putBoolean("enableGroupMms", true);
        sDefaults.putBoolean("enableMMSDeliveryReports", false);
        sDefaults.putBoolean("enabledMMS", true);
        sDefaults.putBoolean("enableMMSReadReports", false);
        sDefaults.putBoolean("enableMultipartSMS", true);
        sDefaults.putBoolean("enabledNotifyWapMMSC", false);
        sDefaults.putBoolean("sendMultipartSmsAsSeparateMessages", false);
        sDefaults.putBoolean("config_cellBroadcastAppLinks", true);
        sDefaults.putBoolean("enableSMSDeliveryReports", true);
        sDefaults.putBoolean("supportHttpCharsetHeader", false);
        sDefaults.putBoolean("supportMmsContentDisposition", true);
        sDefaults.putBoolean("mmsCloseConnection", false);
        sDefaults.putInt("aliasMaxChars", 48);
        sDefaults.putInt("aliasMinChars", 2);
        sDefaults.putInt("httpSocketTimeout", 60000);
        sDefaults.putInt("maxImageHeight", 480);
        sDefaults.putInt("maxImageWidth", 640);
        sDefaults.putInt("maxMessageSize", 0x4b000);
        sDefaults.putInt("maxMessageTextSize", -1);
        sDefaults.putInt("recipientLimit", 0x7fffffff);
        sDefaults.putInt("smsToMmsTextLengthThreshold", -1);
        sDefaults.putInt("smsToMmsTextThreshold", -1);
        sDefaults.putInt("maxSubjectLength", 40);
        sDefaults.putString("emailGatewayNumber", "");
        sDefaults.putString("httpParams", "");
        sDefaults.putString("naiSuffix", "");
        sDefaults.putString("uaProfTagName", "x-wap-profile");
        sDefaults.putString("uaProfUrl", "");
        sDefaults.putString("userAgent", "");
        sDefaults.putBoolean("allow_non_emergency_calls_in_ecm_bool", true);
        sDefaults.putBoolean("use_rcs_presence_bool", false);
        sDefaults.putBoolean("force_imei_bool", false);
        sDefaults.putInt("cdma_roaming_mode_int", -1);
        sDefaults.putString("rcs_config_server_url_string", "");
        sDefaults.putString("carrier_setup_app_string", "");
        sDefaults.putStringArray("carrier_app_wake_signal_config", new String[] {
            "com.android.carrierdefaultapp/.CarrierDefaultBroadcastReceiver:com.android.internal.telephony.CARRIER_SIGNAL_RESET"
        });
        sDefaults.putStringArray("carrier_app_no_wake_signal_config", null);
        sDefaults.putStringArray("carrier_default_actions_on_redirection_string_array", new String[] {
            "9, 4, 1"
        });
        sDefaults.putStringArray("carrier_default_actions_on_reset_string_array", new String[] {
            "6, 8"
        });
        sDefaults.putStringArray("carrier_default_actions_on_default_network_available_string_array", new String[] {
            (new StringBuilder()).append(String.valueOf(false)).append(": 7").toString(), (new StringBuilder()).append(String.valueOf(true)).append(": 8").toString()
        });
        sDefaults.putStringArray("carrier_default_redirection_url_string_array", null);
        sDefaults.putInt("monthly_data_cycle_day_int", -1);
        sDefaults.putLong("data_warning_threshold_bytes_long", -1L);
        sDefaults.putLong("data_limit_threshold_bytes_long", -1L);
        sDefaults.putStringArray("ratchet_rat_families", new String[] {
            "1,2", "7,8,12", "3,11,9,10,15", "14,19"
        });
        sDefaults.putBoolean("treat_downgraded_video_calls_as_video_calls_bool", false);
        sDefaults.putBoolean("drop_video_call_when_answering_audio_call_bool", false);
        sDefaults.putBoolean("allow_merge_wifi_calls_when_vowifi_off_bool", true);
        sDefaults.putBoolean("allow_add_call_during_video_call", true);
        sDefaults.putBoolean("allow_holding_video_call", true);
        sDefaults.putBoolean("wifi_calls_can_be_hd_audio", true);
        sDefaults.putBoolean("video_calls_can_be_hd_audio", true);
        sDefaults.putBoolean("allow_video_calling_fallback_bool", true);
        sDefaults.putStringArray("ims_reasoninfo_mapping_string_array", null);
        sDefaults.putBoolean("enhanced_4g_lte_title_variant_bool", false);
        sDefaults.putBoolean("notify_vt_handover_to_wifi_failure_bool", false);
        sDefaults.putStringArray("filtered_cnap_names_string_array", null);
        sDefaults.putBoolean("editable_wfc_roaming_mode_bool", false);
        sDefaults.putBoolean("stk_disable_launch_browser_bool", false);
        sDefaults.putBoolean("persist_lpp_mode_bool", false);
        sDefaults.putStringArray("carrier_wifi_string_array", null);
        sDefaults.putInt("network_notification_delay_int", -1);
        sDefaults.putInt("emergency_notification_delay_int", -1);
        sDefaults.putBoolean("allow_ussd_requests_via_telephony_manager_bool", true);
        sDefaults.putBoolean("support_3gpp_call_forwarding_while_roaming_bool", true);
        sDefaults.putBoolean("notify_international_call_on_wfc_bool", false);
        sDefaults.putStringArray("call_forwarding_blocks_while_roaming_string_array", null);
        sDefaults.putInt("lte_earfcns_rsrp_boost_int", 0);
        sDefaults.putStringArray("boosted_lte_earfcns_string_array", null);
        sDefaults.putBoolean("disable_voice_barring_notification_bool", false);
        sDefaults.putInt("imsi_key_availability_int", 0);
        sDefaults.putString("imsi_key_download_url_string", null);
        sDefaults.putBoolean("convert_cdma_caller_id_mmi_codes_while_roaming_on_3gpp_bool", false);
        sDefaults.putStringArray("non_roaming_operator_string_array", null);
        sDefaults.putStringArray("roaming_operator_string_array", null);
    }
}
